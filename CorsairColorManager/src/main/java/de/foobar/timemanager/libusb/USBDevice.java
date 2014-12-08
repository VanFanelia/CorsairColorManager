package de.foobar.timemanager.libusb;

import org.usb4java.*;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Editor: van on 07.12.14.
 */
public class USBDevice {

	public USBDevice() {
	}

	public static void main (final String[] args)
	{

		final Context context = new Context();
		final int result = LibUsb.init(context);
		if (result != LibUsb.SUCCESS)
		{
			throw new LibUsbException("Unable to initialize libusb.", result);
		}

		showAllDevices();


		//1b1c:1b13 Corsair
		final Device keyboard = findDevice(Short.valueOf("1b1c", 16), Short.valueOf("1b13", 16));

		System.out.println("\n \n \n \n" + keyboard.toString());
		System.out.println(getDeviceDescriptor(keyboard));

		// send color

		final HashMap<Integer, Color> testColor = new HashMap<Integer, Color>();
		for(int i = 0; i< 144; i++ )
		{
			testColor.put(i, randomColor());
		}
		sendColors(keyboard, testColor);


		LibUsb.exit(context);
	}

	private static Color randomColor() {
		final int color = (int) (Math.random()* 12);
		switch (color) {
			case 0:
				return Color.BLACK;
			case 1:
				return Color.WHITE;
			case 2:
				return Color.RED;
			case 3:
				return Color.GREEN;
			case 4:
				return Color.BLUE;
			case 5:
				return Color.YELLOW;
			case 6:
				return Color.ORANGE;
			case 7:
				return Color.CYAN;
			case 8:
				return Color.GRAY;
			case 9:
				return Color.PINK;
			case 10:
				return Color.LIGHT_GRAY;
			case 11:
				return Color.DARK_GRAY;
			default:
				return Color.WHITE;
		}

	}


	public static void showAllDevices(){

		// Read the USB device list
		final DeviceList list = new DeviceList();
		int result = LibUsb.getDeviceList(null, list);
		if (result < 0) throw new LibUsbException("Unable to get device list", result);

		try
		{
			// Iterate over all devices and scan for the right one
			for (final Device device: list)
			{
				final DeviceDescriptor descriptor = new DeviceDescriptor();
				result = LibUsb.getDeviceDescriptor(device, descriptor);
				if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);

				System.out.println(descriptor.toString());
			}
		}
		finally
		{
			// Ensure the allocated device list is freed
			LibUsb.freeDeviceList(list, true);
		}

	}

	public static DeviceDescriptor getDeviceDescriptor(final Device device)
	{
		final DeviceDescriptor descriptor = new DeviceDescriptor();
		final int result = LibUsb.getDeviceDescriptor(device, descriptor);
		if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
		return descriptor;
	}

	public static Device findDevice(final short vendorId,final  short productId)
	{
		// Read the USB device list
		final DeviceList list = new DeviceList();
		int result = LibUsb.getDeviceList(null, list);
		if (result < 0) throw new LibUsbException("Unable to get device list", result);

		try
		{
			// Iterate over all devices and scan for the right one
			for (final Device device: list)
			{
				final DeviceDescriptor descriptor = new DeviceDescriptor();
				result = LibUsb.getDeviceDescriptor(device, descriptor);
				if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
				if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId) return device;
			}
		}
		finally
		{
			// Ensure the allocated device list is freed
			LibUsb.freeDeviceList(list, true);
		}

		// Device not found
		return null;
	}

	public static void sendColors(final Device device, final Map<Integer, Color> colorMap)
	{
		final byte[] red_val = new byte[144];
		final byte[] green_val = new byte[144];
		final byte[] blue_val = new byte[144];
		for(int i = 0; i < 144; i++)
		{

			final Color color = colorMap.containsKey(i) ? colorMap.get(i) : Color.white;
			red_val[i] =   (byte) (7 - (color.getRed() / 32));
			green_val[i] = (byte) (7 - (color.getGreen() / 32));
			blue_val[i] =  (byte) (7 - (color.getBlue() / 32));
		}


		// update_keyboard(), send packeges;
		final byte[][] data_pkt = new byte[5][64];

		// Perform USB control message to keyboard
		//
		// Request Type:  0x21
		// Request:       0x09
		// Value          0x0300
		// Index:         0x03
		// Size:          64

		data_pkt[0][0] = 0x7F;
		data_pkt[0][1] = 0x01;
		data_pkt[0][2] = 0x3C;

		data_pkt[1][0] = 0x7F;
		data_pkt[1][1] = 0x02;
		data_pkt[1][2] = 0x3C;

		data_pkt[2][0] = 0x7F;
		data_pkt[2][1] = 0x03;
		data_pkt[2][2] = 0x3C;

		data_pkt[3][0] = 0x7F;
		data_pkt[3][1] = 0x04;
		data_pkt[3][2] = 0x24;

		data_pkt[4][0] = 0x07;
		data_pkt[4][1] = 0x27;
		data_pkt[4][4] = -40; // 0xD8;

		for(int i = 0; i < 60; i++)
		{
			data_pkt[0][i+4] = (byte) (red_val[i*2+1] << 4 | red_val[i*2]);
		}

		for(int i = 0; i < 12; i++)
		{
			data_pkt[1][i+4] = (byte) (red_val[i*2+121] << 4 | red_val[i*2+120]) ;
		}

		for(int i = 0; i < 48; i++)
		{
			data_pkt[1][i+16] = (byte) (green_val[i*2+1] << 4 | green_val[i*2]);
		}

		for(int i = 0; i < 24; i++)
		{
			data_pkt[2][i+4] = (byte) (green_val[i*2+97] << 4 | green_val[i*2+96]);
		}

		for(int i = 0; i < 36; i++)
		{
			data_pkt[2][i+28] = (byte) (blue_val[i*2+1] << 4 | blue_val[i*2]);
		}

		for(int i = 0; i < 36; i++)
		{
			data_pkt[3][i+4] = (byte) (blue_val[i*2+73] << 4 | blue_val[i*2+72]);
		}

		final DeviceHandle handle = new DeviceHandle();
		final int result = LibUsb.open(device, handle);
		if (result != LibUsb.SUCCESS){
			throw new LibUsbException("Unable to open USB device", result);
		}
		try
		{
			final ByteBuffer buffers[] = new ByteBuffer[5];

			for(int i = 0; i< 5; i++)
			{
				buffers[i] = ByteBuffer.allocateDirect(64);
				//final byte[] toTransfer = convertToByteArray(data_pkt[i]);
				//buffers[i].put(toTransfer);
				buffers[i].put(data_pkt[i]);

				final int resultUsb = LibUsb.controlTransfer(handle, (byte) 0x21, (byte) 0x09, (short) 0x0300, (short) 0x03, buffers[i], 1000);
				if (resultUsb < 0)
				{
					throw new LibUsbException("Control transfer failed", resultUsb);
				}
				System.out.println(resultUsb + " bytes sent");
			}
		}
		finally
		{
			LibUsb.close(handle);
		}

		/*
		usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[0], 64, 1000);
		usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[1], 64, 1000);
		usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[2], 64, 1000);
		usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[3], 64, 1000);
		usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[4], 64, 1000);
		*/
	}

	public static byte[] convertToByteArray(final int[] intArray)
	{
		final byte[] result = new byte[intArray.length];
		for(int i=0; i < intArray.length; i++)
		{
			result[i] = (byte) (intArray[i] > 127 ? intArray[i] - 255: intArray[i]) ;
		}
		return result;
	}

}
