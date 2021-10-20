package br.com.vhclaw.timesheet.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable{
	

	private static final long serialVersionUID = 1L;
		private Instant timestamp;
		private Integer staus;
		private String error;
		private String message;
		private String path;
		
		public StandardError() {
			
		}

		public Instant getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Instant timestamp) {
			this.timestamp = timestamp;
		}

		public Integer getStaus() {
			return staus;
		}

		public void setStaus(Integer staus) {
			this.staus = staus;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

}
