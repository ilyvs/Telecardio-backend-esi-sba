package dz.esi.gestioncompte.model;


public enum Sex {

    Homme('H'), FEMME('F');

    char name;

    Sex(char name) {
        this.name = name;
    }

    public char getSex() {
        return name;
    }
}
