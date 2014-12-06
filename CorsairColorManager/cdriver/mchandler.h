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

  std::string get (char* key) {

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

      response= "ffffffff";

      int x = 1;
      std::string resultStr;
      while (return_value= memcached_fetch(memc, return_key, &return_key_length, &return_value_length, &flags, &rc))
      {
        if(return_value != NULL)
        {
            response = return_value;
            resultStr = "";
            resultStr = resultStr.append(response);
            free(response);
            //printf("Layer %d response %s \n", x, response);
        }
        //free(return_value);
        x++;
      }
      //printf("found:  %s \n", response);
      return resultStr;

  }

  void remove(const std::string &key)
  {
    // TODO: Implement
  }


};


