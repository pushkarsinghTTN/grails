package enumeration;

public enum Seriousness{
    SERIOUS,VERYSERIOUS,CASUAL;

    public static Seriousness convert(String str){
        if("SERIOUS"==str.toUpperCase()){
            return Seriousness.SERIOUS;
        }
        else if("VERYSERIOUS"==str.toUpperCase())
            return Seriousness.VERYSERIOUS;
        else
            return Seriousness.CASUAL;
    }
}
