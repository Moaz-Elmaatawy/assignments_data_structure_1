package eg.edu.alexu.csd.datastructure.hangman.cs22;

public class Hangman implements IHangman {
    private String secretword;
    private String [] dictionary;
    static int max_guess;
    static int towin;
    char[]ans;
    Hangman(int i){
        this.max_guess=i;
    }
    @Override
    public void setDictionary(String[] words) {
        dictionary=words;
    }

    @Override
    public String selectRandomSecretWord() {
        int a=(int)(dictionary.length*Math.random());
        secretword=dictionary[a];
        towin=secretword.length();
        return dictionary[a];
    }

    @Override
    public String guess(Character c) throws Exception {
        if(secretword.indexOf(c)!=-1)
            for(int i=0;i<secretword.length();++i) {
                if (secretword.charAt(i) == c) {
                    ans[i] = c;
                    --towin;
                }
            }
        else{
            setMaxWrongGuesses(Hangman.max_guess-1);
            System.out.println("Wrong guess");
        }
        String str=new String(ans);
        return str;
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        max_guess=max;
    }

}
