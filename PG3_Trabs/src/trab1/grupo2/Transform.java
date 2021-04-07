package trab1.grupo2;

public abstract class Transform {
    //Variaveis de instancia
    private final String name;

    //Construtores
    protected Transform(String nm) {
        this.name = nm;
    }

    //Metodos de instancia
    public abstract String[] apply(String ... s) throws TransformException;


    @Override
    public String toString() {
        return this.name;
    }

    public static String getString(Transform[] tfs, String sep) {

        StringBuilder catString = new StringBuilder();
        int i;

        for(i = 0; i < tfs.length-1; i++) {
            catString.append(tfs[i].toString()).append(sep);
        }
        catString.append(tfs[i + 1].toString());
        return catString.toString();
    }
}
