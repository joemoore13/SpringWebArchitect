package com.atomrain.labs.architect.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ServiceException extends Exception {
	
	/**
	 * Exception may be serialized.
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private Object[] args;
	
	/**
	 * Default constructor.
	 */
	public ServiceException() {}
	
	/**
	 * 
	 */
	public ServiceException(String code) {
		this.code = code;
	}
	
	/**
	 * 
	 */
	public ServiceException(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}

	/**
	 * The error code from this service exception.
	 */
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return Arguements that may be passed to a message output.
	 */
	public Object[] getArgs() {
		return this.args;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	/**
	 * @return The joined stack trace.
	 */
	public String joinStackTrace() {
	    StringWriter writer = null;
	    try {
	        writer = new StringWriter();
	        joinStackTrace(this, writer);
	        return writer.toString();
	    }
	    finally {
	        if (writer != null)
	            try {
	                writer.close();
	            } catch (IOException e1) {
	                // ignore
	            }
	    }
	}

	private void joinStackTrace(Throwable e, StringWriter writer) {
	    PrintWriter printer = null;
	    try {
	        printer = new PrintWriter(writer);

	        while (e != null) {

	            printer.println(e);
	            StackTraceElement[] trace = e.getStackTrace();
	            for (int i = 0; i < trace.length; i++)
	                printer.println("\tat " + trace[i]);

	            e = e.getCause();
	            if (e != null)
	                printer.println("Caused by:\r\n");
	        }
	    }
	    finally {
	        if (printer != null)
	            printer.close();
	    }
	}
}
