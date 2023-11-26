package classes;

import java.util.HashSet;
import java.util.Set;

public class PokemonTeam {
	
	private static Set<String> valoresUnicos = new HashSet<>();
	private String name;
	private User u1;
	private Pokemon p1;
	private Pokemon p2;
	private Pokemon p3;
	private Pokemon p4;
	private Pokemon p5;
	private Pokemon p6;
	
	public PokemonTeam() {
		super();
	}
	
	public PokemonTeam(String name, User u1) {
		super();
		if (valoresUnicos.contains(name)) {
            throw new IllegalArgumentException("El nombre ya existe, intentalo de nuevo.");
        }
        this.name = name;
        valoresUnicos.add(name);
	}
	
	public String getName() {
		return name;
	}
	
	public User getU1() {
		return u1;
	}
	public void setU1(User u1) {
		this.u1 = u1;
	}
	public Pokemon getP1() {
		return p1;
	}
	public void setP1(Pokemon p1) {
		this.p1 = p1;
	}
	public Pokemon getP2() {
		return p2;
	}
	public void setP2(Pokemon p2) {
		this.p2 = p2;
	}
	public Pokemon getP3() {
		return p3;
	}
	public void setP3(Pokemon p3) {
		this.p3 = p3;
	}
	public Pokemon getP4() {
		return p4;
	}
	public void setP4(Pokemon p4) {
		this.p4 = p4;
	}
	public Pokemon getP5() {
		return p5;
	}
	public void setP5(Pokemon p5) {
		this.p5 = p5;
	}
	public Pokemon getP6() {
		return p6;
	}
	public void setP6(Pokemon p6) {
		this.p6 = p6;
	}
	
	@Override
	public String toString() {
		return "PokemonTeam [u1=" + u1 + ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", p5=" + p5
				+ ", p6=" + p6 + "]";
	}
	public String toCSVString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s",
                name,
                u1.getUsername(),
                p1.getPokemon(),
                p2.getPokemon(),
                p3.getPokemon(),
                p4.getPokemon(),
                p5.getPokemon(),
                p6.getPokemon()
        );
    } 

}
