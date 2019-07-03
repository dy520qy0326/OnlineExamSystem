package org.dream.www.exam.exception;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.dream.www.common.exception.WoException;
import org.dream.www.common.exception.WoResultCode;

/**
 * BSys系统管理异常类
 * @author cailei
 *
 */
public class ExamException extends WoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1029595888491941192L;
	private final static Logger LOG = LogManager.getLogger(ExamException.class);
	
	public ExamException() {
	}

	public ExamException(WoResultCode code, Object... args) {
		super(code, args);
	}

	public ExamException(Throwable cause, WoResultCode code, Object... args) {
		super(cause, code, args);
	}
}
