package io.pivotal.microservices.exceptions;

public class RedisNotSyncExcpetion extends Exception {
	private static final long serialVersionUID = 6386824520713148640L;
	protected Object notSyncObj;
	public RedisNotSyncExcpetion(Object _notSyncObj){
		notSyncObj=_notSyncObj;
	}
}
