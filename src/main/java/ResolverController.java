import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class ResolverController {

    List<String> listFacts = new ArrayList<>();
    Resolver resolver;

    public ResolverController() {

    }

    public Map<String, List<String>> smartphoneRules() {
        List<String> listaRegras = new ArrayList<>();

        // FUNCIONALIDADE -> SMARTPHONE
        listaRegras.add("Smartphone -> Xiaomi Mi 9");
        listaRegras.add("Smartphone -> Huawei P30 Pro");
        listaRegras.add("Smartphone -> Redmi Note 7");
        listaRegras.add("Smartphone -> Moto G7 Plus");
        listaRegras.add("Smartphone -> google pixel 2 xl");
        listaRegras.add("Smartphone -> Motorola One Vision");
        listaRegras.add("Smartphone -> LG K12 Plus");
        listaRegras.add("Celular para Jogos -> Asus ROG Phone 2");
        listaRegras.add("Celular para Jogos -> Razer Phone 2");
        listaRegras.add("8GB RAM+ -> Asus ROG Phone 2");
        listaRegras.add("8GB RAM+ -> OnePlus 7 Pro");
        listaRegras.add("DualChip -> Zenfone 5");
        listaRegras.add("DualChip -> Redmi Note 7");
        listaRegras.add("4g -> Motorola One Vision");
        listaRegras.add("4g -> Xiaomi Mi 9");
        listaRegras.add("Melhor Camera -> google pixel 2 xl");
        listaRegras.add("Melhor Camera -> Huawei P30 Pro");
        listaRegras.add("Reconhecimento Facial -> Galaxy Note 10");
        listaRegras.add("4K Videos -> Galaxy S10 Plus");
        listaRegras.add("4K Videos -> google pixel 2 xl");
        listaRegras.add("Full HD -> Huawei Mate 20 Pro");
        listaRegras.add("Full HD -> LG K12 Plus");
        listaRegras.add("Bateria de 4200 mAh -> Huawei Mate 20 Pro");
        listaRegras.add("Protecao contra agua -> iPhone XS Max");
        listaRegras.add("Protecao contra agua -> Galaxy S10 Plus");
        listaRegras.add("Protecao contra agua -> iPhone XS Max");
        listaRegras.add("Cartao de memoria -> Galaxy A7");
        listaRegras.add("Cartao de memoria -> Moto G7 Plus");
        listaRegras.add("5g -> Galaxy S10 Plus");
        listaRegras.add("Budget -> Xiaomi Pocophone F1");
        listaRegras.add("IOS -> iPhone XS Max");

        Map<String, List<String>> hash = makeHashExemplo(listaRegras);
        return hash;
    }

    public List<String> getFuncionalidades() {
        List<String> facts = new ArrayList<>();
        facts.add("Smartphone");
        facts.add("Celular para Jogos");
        facts.add("8GB RAM+");
        facts.add("DualChip");
        facts.add("4g");
        facts.add("5g");
        facts.add("Melhor Camera");
        facts.add("Reconhecimento Facial");
        facts.add("4K Videos");
        facts.add("Full HD");
        facts.add("Bateria de 4200 mAh");
        facts.add("Protecao contra agua");
        facts.add("Cartao de memoria");
        facts.add("Budget");
        facts.add("IOS");
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


    /*
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



        //OLD STUFF
        //EXEMPLO DO MOTOR DE INFERÊNCIA COM OS 2 TIPOS DE ENCADEAMENTO

        Scanner read = new Scanner(System.in);
        resolver = new Resolver(setRules(), setFacts());
        System.out.println("Encadeamento pra frente");
        System.out.println(resolver.forwardResult() + "\n");
        System.out.println("\n\nEncadeamento pra trás");
        System.out.println("Escolha o objetivo dentre os seguintes: ");
        System.out.println(resolver.getVariables());
        System.out.println(resolver.backwardResult(read.next()));

    }
    */
}
