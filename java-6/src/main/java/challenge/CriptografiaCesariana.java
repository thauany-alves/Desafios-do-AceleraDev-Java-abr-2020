package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        texto = validar(texto);
        texto = converter(texto, 3);
        return texto;

    }

    @Override
    public String descriptografar(String texto) {
        texto = validar(texto);
        texto = converter(texto, -3);
        return texto;
    }

    public String validar(String texto){
        if (texto.isEmpty()) throw new IllegalArgumentException("texto vazio");
        if (texto == null) throw new NullPointerException("texto nulo");
        return texto.toLowerCase();
    }

    public String converter(String texto, int chave){
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        char chrTexto[] = texto.toCharArray();
        String convertido = "";

        for (char caracter : chrTexto){
            int posicao = alfabeto.indexOf(caracter);
            if (posicao != -1){
                posicao = (posicao + chave) % 26;
                convertido+= alfabeto.charAt(posicao);
            }else
                convertido += caracter;
        }
        return  convertido;
    }
}
