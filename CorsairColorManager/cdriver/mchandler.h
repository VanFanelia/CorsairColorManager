#include <stdio.h>
#include <string>
#include <string.h>
using namespace std;
#include <unistd.h>
#include <libmemcached/memcached.h>
#include <libmemcached/memcached.hpp>

class MemcacheHandler
{

  memcached_server_st* server = NULL;
  memcached_st* memc;
  memcached_return rc;

public:
  MemcacheHandler()
  {    

    memc = memcached_create(NULL);
    server = memcached_server_list_append(server, "localhost", 11211, &rc);
    rc = memcached_server_push(memc, server);

    if (rc == MEMCACHED_SUCCESS)
      fprintf(stderr,"Added server successfully\n");
    else
      fprintf(stderr,"Couldn't add server: %s\n", memcached_strerror(memc, rc));
  }

  void set (char* key, char* value) {
    time_t expiry= 0;
    uint32_t flags= 0;
    size_t key_length = strlen(key);
    size_t value_length = strlen(value);
    
    rc= memcached_set(memc, key, key_length, value,
                        value_length, expiry, flags);
    if (rc == MEMCACHED_SUCCESS) {
        fprintf(stderr,"Key %s stored successfully\n",key);
    } else {
        fprintf(stderr,"Couldn't store key: %s \n",memcached_strerror(memc, rc));
    }
  }

  std::string get (const char* key) {

      memcached_return_t rc;

      char keys[5][10];
      for(int i = 0; i < 5; i++)
      {
        snprintf(keys[i], sizeof keys[i], "%s-%d", key, (i+1));
        //printf("load key: %s \n", keys[i]);
      }


      char* response;
      size_t key_length[]= {strlen(keys[0]), strlen(keys[1]), strlen(keys[2]), strlen(keys[3]), strlen(keys[4])};
      uint32_t flags;

      char return_key[MEMCACHED_MAX_KEY];
      size_t return_key_length;
      char *return_value;
      size_t return_value_length;

      char *mkey[] {keys[0], keys[1], keys[2], keys[3], keys[4]};

      rc= memcached_mget(memc, mkey, key_length, 5);

      int x = 1;
      std::string resultStr;
      std::string oldColor = "ffffffff";
      response = "ffffffff";
      while (return_value= memcached_fetch(memc, return_key, &return_key_length, &return_value_length, &flags, &rc))
      {
        if(return_value != NULL)
        {
            response = return_value;
            resultStr = response;
            const char* src = resultStr.c_str();
            const char* dst = oldColor.c_str();
            resultStr = porterDuff(src, dst);
            oldColor = resultStr;
            free(response);
            //printf("Layer %d response %s \n", x, response);
        }
        x++;
      }
      //printf("found:  %s \n", response);
      return resultStr;

  }

  void remove(const std::string &key)
  {
    // TODO: Implement
  }

  std::string porterDuff(const char* src, const char* dst)
  {
    int src_red, src_green, src_blue, dst_red, dst_green, dst_blue;
    double src_alpha, dst_alpha;

    src_red = convertHexCodeToInt(src[0], src[1]);
    src_green = convertHexCodeToInt(src[2], src[3]);
    src_blue = convertHexCodeToInt(src[4], src[5]);
    src_alpha = convertHexCodeToInt(src[6], src[7]);

    //dest
    dst_red = convertHexCodeToInt(dst[0], dst[1]);
    dst_green = convertHexCodeToInt(dst[2], dst[3]);
    dst_blue = convertHexCodeToInt(dst[4], dst[5]);
    dst_alpha = convertHexCodeToInt(dst[6], dst[7]);

    double final_alpha;

    int final_int_alpha, final_red, final_green, final_blue;

    src_alpha = src_alpha / 255;
    dst_alpha = dst_alpha / 255;
    final_alpha = (src_alpha + dst_alpha) - (src_alpha * dst_alpha);
    src_red = src_red * src_alpha;
    src_green = src_green * src_alpha;
    src_blue = src_blue * src_alpha;

    dst_red = dst_red * dst_alpha;
    dst_green = dst_green * dst_alpha;
    dst_blue = dst_blue * dst_alpha;

    final_red = (src_red + dst_red * (1- src_alpha)) / final_alpha;
    final_green = (src_green + dst_green * (1- src_alpha)) / final_alpha;
    final_blue = (src_blue + dst_blue * (1- src_alpha)) / final_alpha;

    final_int_alpha = final_alpha * 255;

    std::stringstream sstream;
    sstream << std::hex << final_red;
    sstream << std::hex << final_green;
    sstream << std::hex << final_blue;
    sstream << std::hex << final_int_alpha;

    std::string result = sstream.str();

    printf("%f, %d, %d, %d and alpha as int: %d \n \n",final_alpha, final_red, final_green, final_blue, final_int_alpha);
    return result;
  }

  int convertHexCodeToInt(char first, char second) {
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
    return result;
  }

};


