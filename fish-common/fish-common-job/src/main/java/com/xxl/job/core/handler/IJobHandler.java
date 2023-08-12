package com.xxl.job.core.handler;

/**
 * job handler
 *
 * @author xuxueli
 * @since 2015-12-19 19:06:38
 */
public abstract class IJobHandler {

	/**
	 * execute handler, invoked when executor receives a scheduling request
	 * @throws Exception e
	 */
	public abstract void execute() throws Exception;

	/**
	 * init handler, invoked when JobThread init
	 */
	public void init() throws Exception {
		// do something
	}

	/**
	 * destroy handler, invoked when JobThread destroy
	 */
	public void destroy() throws Exception {
		// do something
	}

}
