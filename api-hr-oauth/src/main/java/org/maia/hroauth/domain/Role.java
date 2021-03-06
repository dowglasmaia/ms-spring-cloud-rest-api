package org.maia.hroauth.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private String roleName;

}
