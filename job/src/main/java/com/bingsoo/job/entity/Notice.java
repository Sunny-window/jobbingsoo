package com.bingsoo.job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nt_code;

	@ManyToOne
	@JoinColumn(name = "reciever")
	@ToString.Exclude
	private Member reciever;
	@ManyToOne
	@JoinColumn(name = "sender")
	@ToString.Exclude
	private Member sender;
	private String message;
	private String type;
}
