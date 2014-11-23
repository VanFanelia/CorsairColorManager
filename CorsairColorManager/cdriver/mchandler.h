#include <stdio.h>
#include <string>
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

  char* get (char* key) {
    uint32_t flags= 0;
    memcached_return error;
    size_t return_value_length;
    char *response  = memcached_get(memc, key, strlen (key), &return_value_length, &flags, &error);
    if(error != 0){
      //fprintf(stderr,"Couldn't get key (%s): %s \n", key, memcached_strerror(memc, error));
      return NULL;
    }
 
    return response;

  }

  void remove(const std::string &key)
  {
    // TODO: Implement
  }


};


