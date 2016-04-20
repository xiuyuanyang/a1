package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	private static JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), Global.getConfig("redis_server"));

	public synchronized static Jedis getJedis() {

		if (jedisPool != null) {
			Jedis resource = jedisPool.getResource();
			return resource;
		} else {
			return null;
		}
	}

	public static boolean exist(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(key);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static byte[] lpop(byte[] key) {
		byte[] result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lpop(key);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static Long rpush(byte[] key, byte[] obj) {
		Long result = null;

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.rpush(key, obj);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static Long del(String key) {
		Long result = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			result = jedis.del(key);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static Long del(byte[] key) {
		Long result = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			result = jedis.del(key);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static String setex(byte[] key, int seconds, byte[] value) {
		String result = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			result = jedis.setex(key, seconds, value);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static String setex(String key, int seconds, String value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.setex(key, seconds, value);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}

		return value;
	}

	public static byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}

		return value;
	}

	public static Long setExpire(byte[] key, int expire) {
		Long value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.expire(key, expire);
		} catch (Exception e) {

			if (null != jedis) {
				jedis.close();
			}

		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}

		return value;
	}

}
