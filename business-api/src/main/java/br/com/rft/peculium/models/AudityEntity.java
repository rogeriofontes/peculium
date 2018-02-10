package br.com.rft.peculium.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rft.peculium.util.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
// @Audited
@EntityListeners(AuditingEntityListener.class)
@Data public abstract class AudityEntity implements Serializable {

	private static final long serialVersionUID = -8812000052333532897L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreatedDate
	@NotNull
	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@Getter
	@Setter
	private Date createdDate = DateUtil.localDateTimeToDate(LocalDateTime.now());

	@CreatedBy
	@NotNull
	@Column(name = "create_by")
	@JsonIgnore
	@Getter
	@Setter
	private String createBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date lastModifiedDate;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	@JsonIgnore
	@Getter
	@Setter
	private String lastModifiedBy;
}
