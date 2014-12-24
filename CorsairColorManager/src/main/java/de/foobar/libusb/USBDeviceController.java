package de.foobar.libusb;

import de.foobar.color.ColorHelper;
import de.foobar.exception.ProgramParseException;
import org.usb4java.*;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Editor: van on 07.12.14.
 */
public class USBDeviceController {

	private Context context;

	private DeviceHandle handle ;

	private Device device;

	public USBDeviceController() {
		final Context context = new Context();
	}

	public static void main (final String[] args) throws ProgramParseException
	{
		final USBDeviceController controller = new USBDeviceController();
		try {

			controller.init();

			//controller.showAllDevices();

			System.out.println("\n \n \n \n" + controller.device.toString());
			System.out.println(controller.getDeviceDescriptor(controller.device));

			// send color
			final HashMap<Integer, Color> testColor = new HashMap<Integer, Color>();
			for (int i = 0; i < 144; i++) {
				testColor.put(i, ColorHelper.randomColor());

			}
			controller.sendColors(testColor);
		}catch (final Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			controller.shutdownLibUsb();
		}

	}

	public void init() throws ProgramParseException
	{
		this.initLibUsb();
		this.device = getCorsairKeyboard();
		this.handle = initDevice(this.device, false);
	}

	private Device getCorsairKeyboard() throws ProgramParseException
	{
		final Device keyboard = findDevice(Short.valueOf("1b1c", 16), Short.valueOf("1b13", 16));
		if(keyboard == null){
			throw new ProgramParseException("Keyboard not found");
		}
		return keyboard;
	}

	private void initLibUsb()
	{
		final int result = LibUsb.init(this.context);
		if (result != LibUsb.SUCCESS)
		{
			throw new LibUsbException("Unable to initialize libusb.", result);
		}

	}

	private DeviceHandle initDevice(final Device device, final boolean skipKernelAttach)
	{
		final DeviceHandle handle = new DeviceHandle();
		final int result = LibUsb.open(device, handle);
		if (result != LibUsb.SUCCESS){
			throw new LibUsbException("Unable to open USB device", result);
		}

		final boolean kernelAttachAvailable = !skipKernelAttach && (LibUsb.kernelDriverActive(handle, 3) == 1);
			//	&& (LibUsb.hasCapability(LibUsb.CAP_SUPPORTS_DETACH_KERNEL_DRIVER));
		if(kernelAttachAvailable)
		{
			final int resultDetachKernel = LibUsb.detachKernelDriver(handle,3);
			System.out.println("Detach Kernel result: " + resultDetachKernel);
		}

		final int resultClaimInterface = LibUsb.claimInterface(handle, 3);
		System.out.println("Claim Interface result: " + resultClaimInterface);

		return handle;

	}

	public void shutdownLibUsb()
	{
		if(handle == null) {
			return;
		}
		try {
			LibUsb.releaseInterface(this.handle, 3);
			/*final boolean kernelAttached = (LibUsb.kernelDriverActive(this.handle, 3) == 0);
			if (kernelAttached)
			{
				final int attachKernelDriver = LibUsb.attachKernelDriver(this.handle,  3);
				if (attachKernelDriver != LibUsb.SUCCESS)
				{
					System.out.println("Unable to re-attach kernel driver :" + attachKernelDriver);
				}
			}*/
		}catch (final Exception e){
			System.out.println("Error on release Interface: " + e.getMessage());
		}finally {
			try {
				LibUsb.close(this.handle);
				LibUsb.exit(this.context);
			} catch (final Exception e)
			{
				System.out.println("Error on close Interface: " + e.getMessage());
			}
		}
	}

	public void showAllDevices(){

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

	public DeviceDescriptor getDeviceDescriptor(final Device device)
	{
		final DeviceDescriptor descriptor = new DeviceDescriptor();
		final int result = LibUsb.getDeviceDescriptor(device, descriptor);
		if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
		return descriptor;
	}

	public Device findDevice(final short vendorId,final  short productId)
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

	public synchronized void sendColors(final Map<Integer, Color> colorMap)
	{
		long time = System.currentTimeMillis();
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

		final ByteBuffer buffers[] = new ByteBuffer[5];

		for(int i = 0; i< 5; i++)
		{
			buffers[i] = ByteBuffer.allocateDirect(64);
			//final byte[] toTransfer = convertToByteArray(data_pkt[i]);
			//buffers[i].put(t oTransfer);
			buffers[i].put(data_pkt[i]);

			//final int resultUsb = LibUsb.controlTransfer(this.handle, (byte) (LibUsb.REQUEST_TYPE_CLASS | LibUsb.RECIPIENT_INTERFACE),
			//		(byte) 0x09, (short) 2, (short) 1, buffers[i], 1000);


			final int resultUsb = LibUsb.controlTransfer(handle, (byte) 0x21, (byte) 0x09, (short) 0x0300, (short) 0x03, buffers[i], 1000);
			if (resultUsb < 0)
			{
				throw new LibUsbException("Control transfer failed", resultUsb);
			}
			//System.out.println(resultUsb + " bytes sent");
		}
		//System.out.println((System.currentTimeMillis() - time) + " ms to send usb data");
	}

}
