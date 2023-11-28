package br.com.prestoshat.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.prestoshat.persistence.model.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="dt_usuario")
public class Usuario implements Serializable {
	
	/**
	 * Serial 
	 */
	private static final long serialVersionUID = 2084772178752768214L;
	
	@Id	
	private String username;
	private String password;
	
	private String user;
	private String email;	
	private int status;
	private int attempts;	
	
	@Column(name="data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;	
	@Column(name="data_alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastAccess;
	
	/**
	 * @return
	 */
	public Status convertStatusToEnum() {
		Status status = null;
		for(Status item : Status.values()) {
			if(this.status == item.ordinal()) {
				status = item;
				break;
			}
		}
		return status;
	}
	
	/**
	 * @param status
	 * @return
	 */
	public int convertEnumToStatus(Status status) {		
		return status.ordinal();
	}

}
