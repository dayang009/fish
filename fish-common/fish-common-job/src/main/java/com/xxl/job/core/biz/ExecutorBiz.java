package com.xxl.job.core.biz;

import com.xxl.job.core.biz.model.*;

/**
 * @author xuxueli
 * @date 2017/3/1
 */
public interface ExecutorBiz {

	/**
	 * beat
	 * @return
	 */
	ReturnT<String> beat();

	/**
	 * idle beat
	 * @param idleBeatParam
	 * @return
	 */
	ReturnT<String> idleBeat(IdleBeatParam idleBeatParam);

	/**
	 * run
	 * @param triggerParam
	 * @return
	 */
	ReturnT<String> run(TriggerParam triggerParam);

	/**
	 * kill
	 * @param killParam
	 * @return
	 */
	ReturnT<String> kill(KillParam killParam);

	/**
	 * log
	 * @param logParam
	 * @return
	 */
	ReturnT<LogResult> log(LogParam logParam);

}
