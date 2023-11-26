package classes;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String username;
	private String password;
	private String firstSurname;
	private String secondSurname;
	private String email;
	private int telephone;
	private List<PokemonTeam> equipos;
	public User(String username, String password, String firstSurname, String secondSurname, String email,
			int telephone) {
		super();
		this.username = username;
		this.password = password;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.email = email;
		this.telephone = telephone;
		this.equipos = new ArrayList<>();
	}
	public List<PokemonTeam> getEquipos() {
		return equipos;
	}
	public void setEquipos(List<PokemonTeam> equipos) {
		this.equipos = equipos;
	}
	public void anadirEquipo(PokemonTeam team) {
		this.equipos.add(team);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstSurname() {
		return firstSurname;
	}
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}
	public String getSecondSurname() {
		return secondSurname;
	}
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstSurname=" + firstSurname
				+ ", secondSurname=" + secondSurname + ", email=" + email + ", telephone=" + telephone + "]";
	}
	public String toCSVString() {
        return String.format("%s;%s;%s;%s;%s;%s",
                username,
                password,
                firstSurname,
                secondSurname,
                email,
                telephone
        );
    }
	public User(User existingUser) {
	    this(
	        existingUser.getUsername(),
	        existingUser.getPassword(),
	        existingUser.getFirstSurname(),
	        existingUser.getSecondSurname(),
	        existingUser.getEmail(),
	        existingUser.getTelephone()
	    );
	}
}
