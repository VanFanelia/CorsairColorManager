#include <stdio.h>
#include <string>
#include <unistd.h>
#include <vector>
#include <libmemcached/memcached.h>
#include <libmemcached/memcached.hpp>

using namespace memcache;
using namespace std;
int main(int argc, char *argv[])
{
  Memcache *client= new Memcache("127.0.0.1:11211");
  time_t expiry= 0;
  uint32_t flags= 0;
  vector<char> value = {'w','o','r','l','d'};
  client->set("hello", value, expiry, flags);
  vector<char> ret_value;
  client->get("hello", ret_value);
    
  std::string s(ret_value.begin(), ret_value.end());
  //printf("value: %c %c %c",ret_value[0],ret_value[1],ret_value[2]);
  printf("value: %s \n", s.c_str());  
}
