package org.maia.hruser.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Email
	@Column(unique = true)
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER) // carrega os dados junto com u usuario.
	@JoinTable(
			name = "tb_user_role", 								//nome da tabela auxilixar
			joinColumns = @JoinColumn(name="user_id"),			//nome do atributo da entidade corrent( 'User' )
			inverseJoinColumns = @JoinColumn(name="role_id")	//nome do atributo da tabela assoçiada ( 'Role' )
			)	
	private Set<Role>roles = new HashSet<>();
	
	
}
