import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Main {

    static List<String> listFacts = new ArrayList<>();
    static Resolver resolver;

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


    private static Map<String, List<String>> makeHashExemplo(List<String> listaRegras) {
        Map<String, List<String>> hash = new HashMap<>();
        List<Regra> res1 = new ArrayList<>();

        //reDAO.beginTransaction();
        for (String string : listaRegras) {

            String key = string.split("->")[0].trim();
            String right = string.split("->")[1].trim();

            Regra re = new Regra();
            re.setAntecedente(key);
            re.setConsequente(right);

            //reDAO.save(re);
            res1.add(re);
        }
        //reDAO.commitTransaction();

        //List<Regra> res = reDAO.listAll();

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
        Scanner read = new Scanner(System.in);
        resolver = new Resolver(setRules(), listFacts);
        System.out.println("Encadeamento pra frente");
        System.out.println(resolver.forwardResult() + "/n");
        System.out.println("Encadeamento pra trás");
        System.out.println("Escolha o objetivo dentre os seguintes: ");
        System.out.println(resolver.getVariables());
        System.out.println(resolver.backwardResult(read.next()));

    }
}
