// compile with:  g++ flashmemcached.cpp -lusb -std=c++11 -Wl,--unresolved-symbols=ignore-in-object-files -o keyboardColorChanger -lmemcached
#include <stdio.h>
#include <usb.h>
#include <math.h>
#include <wchar.h>
#include <string>
using namespace std;
#include <unistd.h>
#include <vector>

#include <algorithm>
#include <string.h>
#include <iostream>     // std::cout, std::ios
#include <sstream>      // std::ostringstream

#include "mchandler.h"
#include "ctime"
#define FALSE 0

static struct usb_device *dev;
struct usb_dev_handle *handle;

static int init_keyboard();

static void update_keyboard();

static void init_memcached_server();

static void read_memcached_server();

static void set_led( int x, int r, int g, int b );

static std::string getColorForKey(char* key);

static int convertColor(char first, char second);

static struct usb_device *find_device(uint16_t vendor, uint16_t product);

static MemcacheHandler handler;

char red_val[144];
char grn_val[144];
char blu_val[144];


static char* key[] = {
  "0","1","2","3","4","5","6","7","8","9",
  "10","11","12","13","14","15","16","17","18","19",
  "20","21","22","23","24","25","26","27","28","29",
  "30","31","32","33","34","35","36","37","38","39",
  "40","41","42","43","44","45","46","47","48","49",
  "50","51","52","53","54","55","56","57","58","59",
  "60","61","62","63","64","65","66","67","68","69",
  "70","71","72","73","74","75","76","77","78","79",
  "80","81","82","83","84","85","86","87","88","89",
  "90","91","92","93","94","95","96","97","98","99",
  "100","101","102","103","104","105","106","107","108","109",
  "110","111","112","113","114","115","116","117","118","119",
  "120","121","122","123","124","125","126","127","128","129",
  "130","131","132","133","134","135","136","137","138","139",
  "140","141","142","143"
};

char data_pkt[5][64] = { 0 };

int main(int argc, char *argv[])
{
    handler = MemcacheHandler();    

    init_keyboard();	


    int i = 0;
    while(1)
    {
      i++;
      clock_t Start = clock();
      for(int j = 0; j < 144; j++)
      {
        string color = getColorForKey(key[j]);
        int red = convertColor(color.at(0),color.at(1));
        int green = convertColor(color.at(2),color.at(3));
	int blue = convertColor(color.at(4),color.at(5));
        set_led( j, red, green, blue);
      }
      //printf("update keyboard \n");
      update_keyboard();
      //printf("finish keyboard \n");
      //printf("paintFrame %d \n", i);
      
      usleep(25000 - ((clock() - Start)));
      //usleep(25000);
    }

    return 0;
}

static int convertColor(char first, char second) {
  int result;
  switch(second) {
    case '0':  result = 0; break;
    case '1':  result = 1; break;
    case '2':  result = 2; break;
    case '3':  result = 3; break;
    case '4':  result = 4; break;
    case '5':  result = 5; break;
    case '6':  result = 6; break;
    case '7':  result = 7; break;
    case '8':  result = 8; break;
    case '9':  result = 9; break;
    case 'a':
    case 'A': result = 10; break;
    case 'b':
    case 'B': result = 11; break;
    case 'c':
    case 'C': result = 12; break;
    case 'd':
    case 'D': result = 13; break;
    case 'e':
    case 'E': result = 14; break;
    case 'f':
    case 'F': result = 15; break;
    default: result = 0; break;
  }//switch	
  switch(first) {
    //case '0':  result = result + 0; break;
    case '1':  result = result + 16; break;
    case '2':  result = result + 32; break;
    case '3':  result = result + 48; break;
    case '4':  result = result + 64; break;
    case '5':  result = result + 80; break;
    case '6':  result = result + 96; break;
    case '7':  result = result + 112; break;
    case '8':  result = result + 128; break;
    case '9':  result = result + 144; break;
    case 'a':
    case 'A': result = result + 160; break;
    case 'b':
    case 'B': result = result + 176; break;
    case 'c':
    case 'C': result = result + 192; break;
    case 'd':
    case 'D': result = result + 208; break;
    case 'e':
    case 'E': result = result + 224; break;
    case 'f':
    case 'F': result = result + 240; break;
    default: result = result + 0; break;
  }//switch
  	
  return result / 32;
}

static void set_led( int led, int r, int g, int b )
{

    if(led >= 144 || led < 0 )
    {
        return;
    }

    if( r > 7 ) r = 7;
    if( g > 7 ) g = 7;
    if( b > 7 ) b = 7;

    r = 7 - r;
    g = 7 - g;
    b = 7 - b;

    red_val[led] = r;
    grn_val[led] = g;
    blu_val[led] = b;
}


static int init_keyboard()
{
    int status = 0;

    printf("Searching for Corsair K70 RGB keyboard...\n");

    dev = find_device(0x1B1C, 0x1B13);

    if(dev == NULL)
    {
        printf("Searching for Corsair K95 RGB keyboard...\n");

        dev = find_device(0x1B1C, 0x1B11);
    }

    if(dev == NULL)
    {
        printf("Corsair K70 RGB keyboard not detected :(\n");
        return 1;
    }
    else
    {
        printf("Corsair K70 RGB keyboard detected successfully :)\n");
    }

    handle = usb_open(dev);

    if(handle == NULL)
    {
        printf("USB device handle did not open :(\n");
        return 1;
    }
    else
    {
        printf("USB device handle opened successfully :)\n");
    }

    status = usb_claim_interface(handle, 3);
    status = usb_detach_kernel_driver_np(handle, 3);

    if(status == 0)
    {
        printf("USB interface claimed successfully :)\n");
    }
    else
    {
        printf("USB interface claim failed with error %d :(\n", status);
        return 1;
    }

}

static void update_keyboard()
{
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
    data_pkt[4][4] = 0xD8;

    for(int i = 0; i < 60; i++)
    {
        data_pkt[0][i+4] = red_val[i*2+1] << 4 | red_val[i*2];
    }

    for(int i = 0; i < 12; i++)
    {
        data_pkt[1][i+4] = red_val[i*2+121] << 4 | red_val[i*2+120];
    }

    for(int i = 0; i < 48; i++)
    {
        data_pkt[1][i+16] = grn_val[i*2+1] << 4 | grn_val[i*2];
    }

    for(int i = 0; i < 24; i++)
    {
        data_pkt[2][i+4] = grn_val[i*2+97] << 4 | grn_val[i*2+96];
    }

    for(int i = 0; i < 36; i++)
    {
        data_pkt[2][i+28] = blu_val[i*2+1] << 4 | blu_val[i*2];
    }

    for(int i = 0; i < 36; i++)
    {
        data_pkt[3][i+4] = blu_val[i*2+73] << 4 | blu_val[i*2+72];
    }

    usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[0], 64, 1000);
    usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[1], 64, 1000);
    usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[2], 64, 1000);
    usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[3], 64, 1000);
    usb_control_msg(handle, 0x21, 0x09, 0x0300, 0x03, data_pkt[4], 64, 1000);
}


static struct usb_device *find_device(uint16_t vendor, uint16_t product)
{
    struct usb_bus *bus;
    struct usb_device *dev;
    struct usb_bus *busses;

    usb_init();
    usb_find_busses();
    usb_find_devices();

    busses = usb_get_busses();

    for(bus = busses; bus; bus = bus->next)
    {
        for(dev = bus->devices; dev; dev = dev->next)
        {
            if((dev->descriptor.idVendor == vendor) && (dev->descriptor.idProduct == product))
            {
                return dev;
            }
        }
    }

    return NULL;
}

static std::string getColorForKey(char* key)
{
  //printf("start MyCache and get color for: %s \n", key);
  char* color = handler.get(key);
  if (color != NULL)
  {
    std::string strFromChar;
    strFromChar.append(color);
    //printf("Color from DB: %s \n", strFromChar.c_str()); debug only
    return strFromChar;
  }


  std::string defaultColor = "ffffffff"; //default white
  return defaultColor;
}




