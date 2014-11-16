package de.foobar.timemanager.memcached;

import de.foobar.timemanager.common.ColorHelper;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Editor: van on 16.11.14.
 */
public class CustomMemcachedClient extends MemcachedClient {
	public CustomMemcachedClient(final InetSocketAddress... ia) throws IOException {
		super(ia);
	}

	public CustomMemcachedClient(final List<InetSocketAddress> addrs) throws IOException {
		super(addrs);
	}

	public CustomMemcachedClient(final ConnectionFactory cf, final List<InetSocketAddress> addrs) throws IOException {
		super(cf, addrs);
	}

	public void set(final String key, final int ttl, final String value) {
		super.set(key, ttl, value);
	}

	public String get(final String key) {
		final String str = (String) super.get(key);
		if(str == null) {
			return "";
		}
		return str;
	}

	public OperationFuture<Boolean> delete(final String key) {
		return super.delete(key);
	}

	public void set(final int number, final int i, final Color startColor)
	{
		this.set(String.valueOf(number), i, ColorHelper.convertColorToHexRGAString(startColor));
	}
}
