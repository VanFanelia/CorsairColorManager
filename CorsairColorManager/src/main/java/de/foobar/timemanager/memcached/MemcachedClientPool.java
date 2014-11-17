package de.foobar.timemanager.memcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;

import java.io.IOException;

/**
 * Editor: van on 16.11.14.
 */
public class MemcachedClientPool
{
	private static MemcachedClientPool instance = null;
	private static CustomMemcachedClient[] clientPool = new CustomMemcachedClient[20];

	private MemcachedClientPool() throws IOException
	{
		for(int i=0; i<20; i++)
		{
			MemcachedClientPool.clientPool[i] = new CustomMemcachedClient( new BinaryConnectionFactory(), AddrUtil.getAddresses("127.0.0.1:11211"));
		}

	}

	public static synchronized CustomMemcachedClient getInstance() throws IOException {
		if(instance == null) {
			instance = new MemcachedClientPool();
		}
		return instance.getCache();
	}

	public CustomMemcachedClient getCache() {
		CustomMemcachedClient c= null;
		try {
			final int r = (int) (Math.random()* 20);
			final int i = r >= 20? 19: r;
			c = clientPool[i];
		} catch(final Exception e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
}
