package trab1.grupo2;

public abstract class SingleTranform extends Transform {
    
    public SingleTranform(String nm) {
        super(nm);
    }

    public String[] apply(String ... s) {
        String[] sArr = new String[s.length];
        for(int i = 0; i < s.length; i++) {
            sArr[i] = applyForEach(s[i]);
        }
        return sArr;
    }

    protected abstract String applyForEach(String s);

}
