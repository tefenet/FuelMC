package fuelMC.model;

public class Credentials {

    private String token;
    private int exp;
    private String username;
	private int perfil;

    public Credentials() {
    }

    public Credentials(String token, int exp, String username, int perfil) {
        this.token = token;
        this.exp = exp;
        this.username = username;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getPerfil() {
        return perfil;
    }
}

