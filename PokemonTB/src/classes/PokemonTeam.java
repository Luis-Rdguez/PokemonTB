package classes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PokemonTeam {
	
	private static Set<String> valoresUnicos = new HashSet<>();
	private String name;
	private String user;
	private Pokemon p1;
	private Pokemon p2;
	private Pokemon p3;
	private Pokemon p4;
	private Pokemon p5;
	private Pokemon p6;
	
	public PokemonTeam() {
		super();
	}
	
	public PokemonTeam(String name, String user) {
		super();
//		if (valoresUnicos.contains(name)) {
//            throw new IllegalArgumentException("El nombre ya existe, intentalo de nuevo.");
//        }
        this.name = name;
        this.user =user;
//        valoresUnicos.add(name);
	}
	
	public String getName() {
		return name;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PokemonTeam otroEquipo = (PokemonTeam) obj;
        return Objects.equals(name, otroEquipo.name) &&
               Objects.equals(user, otroEquipo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user);
    }
    
	@Override
	public String toString() {
		return "PokemonTeam [name=" + name + ", u1=" + user + ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", p5=" + p5
				+ ", p6=" + p6 + "]";
	}
	public String toCSVString() {
		String pokemon1 = (p1 != null) ? p1.getPokemon() : "";
	    String pokemon2 = (p2 != null) ? p2.getPokemon() : "";
	    String pokemon3 = (p3 != null) ? p3.getPokemon() : "";
	    String pokemon4 = (p4 != null) ? p4.getPokemon() : "";
	    String pokemon5 = (p5 != null) ? p5.getPokemon() : "";
	    String pokemon6 = (p6 != null) ? p6.getPokemon() : "";
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s",
                name,
                user,
                pokemon1,
                pokemon2,
                pokemon3,
                pokemon4,
                pokemon5,
                pokemon6
        );
    } 

}
