import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Main {

    static List<String> listFacts = new ArrayList<>();
    static Resolver resolver;

    private static Map<String, List<String>> smartphoneRules() {
        List<String> listaRegras = new ArrayList<>();

        // FUNCIONALIDADE -> SMARTPHONE
        listaRegras.add("smartphone -> nokia n8");
        listaRegras.add("smartphone -> blackberry q10");
        listaRegras.add("smartphone -> xiaomi mi mix");
        listaRegras.add("smartphone -> htc 10");
        listaRegras.add("smartphone -> galaxy s3");
        listaRegras.add("smartphone -> google pixel xl");
        listaRegras.add("smartphone -> moto e2");
        listaRegras.add("smartphone -> moto g4 play");
        listaRegras.add("smartphone -> moto g4");
        listaRegras.add("mp3 -> siemens gs55-6");
        listaRegras.add("mp3 -> multilaser p3298");
        listaRegras.add("multichip -> multilaser p3298");
        listaRegras.add("4g -> moto e2");
        listaRegras.add("4g -> moto g4 play");
        listaRegras.add("4g -> moto g4");
        listaRegras.add("flash -> xiaomi mi mix");
        listaRegras.add("flash -> htc 10");
        listaRegras.add("flash -> moto g4 play");
        listaRegras.add("flash -> moto g4");
        listaRegras.add("leitor biometrico -> moto g4");
        listaRegras.add("camera frontal -> galaxy s3");
        listaRegras.add("camera frontal -> google pixel xl");
        listaRegras.add("tela hd -> google pixel xl");
        listaRegras.add("giroscopio -> htc 10");
        listaRegras.add("teclado fisico -> blackberry q10");

        Map<String, List<String>> hash = makeHashExemplo(listaRegras);
        return hash;
    }

    private static List<String> getFuncionalidades() {
        List<String> facts = new ArrayList<>();
        facts.add("smartphone");
        facts.add("mp3");
        facts.add("colorido");
        facts.add("multichip");
        facts.add("4g");
        facts.add("flash");
        facts.add("leitor biometrico");
        facts.add("camera frontal");
        facts.add("tela hd");
        facts.add("giroscopio");
        facts.add("teclado fisico");
        return facts;
    }

    //exemplo para o  motor de inferencia
    private static Map<String, List<String>> setRules() {
        List<String> listaRegras = new ArrayList<>();

        listaRegras.add("chove -> nao faz sol");
        listaRegras.add("nubla -> chove");
        listaRegras.add("faz sol -> praia");
        listaRegras.add("chove & nubla -> sono");
        listaRegras.add("chove & nubla & sono -> tempestade");
        listaRegras.add("faz sol -> nao chove");

        Map<String, List<String>> hash = makeHashExemplo(listaRegras);

        return hash;
    }

    //exemplo para o  motor de inferencia
    private static List<String> setFacts() {
        List<String> facts = new ArrayList<>();
        facts.add("nubla");
        facts.add("venta");
        return facts;
    }

    private static Map<String, List<String>> makeHashExemplo(List<String> listaRegras) {
        Map<String, List<String>> hash = new HashMap<>();
        List<Regra> res1 = new ArrayList<>();

        for (String string : listaRegras) {
            String key = string.split("->")[0].trim();
            String right = string.split("->")[1].trim();
            Regra re = new Regra();
            re.setAntecedente(key);
            re.setConsequente(right);
            res1.add(re);
        }
        for (Regra regra : res1) {
            if (!hash.containsKey(regra.getAntecedente())) {
                List<String> values = new ArrayList<>();
                values.add(regra.getConsequente());
                hash.put(regra.getAntecedente(), values);
            } else {
                List<String> values = hash.get(regra.getAntecedente());
                values.add(regra.getConsequente());
                hash.remove(regra.getAntecedente());
                hash.put(regra.getAntecedente(), values);
            }
        }
        return hash;
    }



    public static void main(String[] args) {
        String resp = "";
        Scanner read = new Scanner(System.in);
        List<String> funcionalidades = getFuncionalidades();
        List<String> funcEscolhidas = new ArrayList<>();
        List<String> smartphonesRecomendados;

        System.out.println("ESCOLHA AS FUNCIONALIDADES QUE DESEJA NO SMARTPHONE ENTRE AS SEGUINTES:");
        for(int i = 0; i < funcionalidades.size(); i++){
            System.out.println(funcionalidades.get(i) + " ["+i+"] ");
        }

        System.out.println("Digite 'pronto' quando terminar de escolher.");
        while (!resp.equals("pronto")){
            System.out.println("add funcionalidade: ");
            resp = read.next();
            if(!resp.equals("pronto"))
                funcEscolhidas.add(funcionalidades.get(Integer.parseInt(resp)));
        }

        System.out.println("FUNCIONALIDADES ESCOLHIDAS:");
        for(String func : funcEscolhidas){
            System.out.println(func);
        }

        resolver = new Resolver(smartphoneRules(), funcEscolhidas);
        smartphonesRecomendados = resolver.forwardResult();

        System.out.println("SMARTPHONES RECOMENDADOS:");
        for(int i = funcEscolhidas.size()-1; i < smartphonesRecomendados.size(); i++){
            System.out.println(smartphonesRecomendados.get(i));
        }



        /* OLD STUFF
        //EXEMPLO DO MOTOR DE INFERÊNCIA COM OS 2 TIPOS DE ENCADEAMENTO

        Scanner read = new Scanner(System.in);
        resolver = new Resolver(setRules(), setFacts());
        System.out.println("Encadeamento pra frente");
        System.out.println(resolver.forwardResult() + "\n");
        System.out.println("\n\nEncadeamento pra trás");
        System.out.println("Escolha o objetivo dentre os seguintes: ");
        System.out.println(resolver.getVariables());
        System.out.println(resolver.backwardResult(read.next()));
        */
    }
}
