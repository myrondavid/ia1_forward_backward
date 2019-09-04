package Database;

import java.util.ArrayList;
import java.util.List;

public class Database {
    DatabaseRegra dbR = new DatabaseRegra();
    DatabaseFato dbF = new DatabaseFato();

    public Database() {
    }

    public void addRegras(){
        List<RegraDb> listaRegras = new ArrayList<>();

        // FUNCIONALIDADE -> SMARTPHONE
        listaRegras.add(new RegraDb("Smartphone -> Xiaomi Mi 9"));
        listaRegras.add(new RegraDb("Smartphone -> Huawei P30 Pro"));
        listaRegras.add(new RegraDb("Smartphone -> Redmi Note 7"));
        listaRegras.add(new RegraDb("Smartphone -> Moto G7 Plus"));
        listaRegras.add(new RegraDb("Smartphone -> google pixel 2 xl"));
        listaRegras.add(new RegraDb("Smartphone -> Motorola One Vision"));
        listaRegras.add(new RegraDb("Smartphone -> LG K12 Plus"));
        listaRegras.add(new RegraDb("Celular para Jogos -> Asus ROG Phone 2"));
        listaRegras.add(new RegraDb("Celular para Jogos -> Razer Phone 2"));
        listaRegras.add(new RegraDb("8GB RAM+ -> Asus ROG Phone 2"));
        listaRegras.add(new RegraDb("8GB RAM+ -> OnePlus 7 Pro"));
        listaRegras.add(new RegraDb("DualChip -> Zenfone 5"));
        listaRegras.add(new RegraDb("DualChip -> Redmi Note 7"));
        listaRegras.add(new RegraDb("4g -> Motorola One Vision"));
        listaRegras.add(new RegraDb("4g -> Xiaomi Mi 9"));
        listaRegras.add(new RegraDb("Melhor Camera -> google pixel 2 xl"));
        listaRegras.add(new RegraDb("Melhor Camera -> Huawei P30 Pro"));
        listaRegras.add(new RegraDb("Reconhecimento Facial -> Galaxy Note 10"));
        listaRegras.add(new RegraDb("4K Videos -> Galaxy S10 Plus"));
        listaRegras.add(new RegraDb("4K Videos -> google pixel 2 xl"));
        listaRegras.add(new RegraDb("Full HD -> Huawei Mate 20 Pro"));
        listaRegras.add(new RegraDb("Full HD -> LG K12 Plus"));
        listaRegras.add(new RegraDb("Bateria de 4200 mAh -> Huawei Mate 20 Pro"));
        listaRegras.add(new RegraDb("Protecao contra agua -> iPhone XS Max"));
        listaRegras.add(new RegraDb("Protecao contra agua -> Galaxy S10 Plus"));
        listaRegras.add(new RegraDb("Protecao contra agua -> iPhone XS Max"));
        listaRegras.add(new RegraDb("Cartao de memoria -> Galaxy A7"));
        listaRegras.add(new RegraDb("Cartao de memoria -> Moto G7 Plus"));
        listaRegras.add(new RegraDb("5g -> Galaxy S10 Plus"));
        listaRegras.add(new RegraDb("Budget -> Xiaomi Pocophone F1"));
        listaRegras.add(new RegraDb("IOS -> iPhone XS Max"));

        for(RegraDb regra : listaRegras){
            dbR.persist(regra);
        }
    }

    public void addFatos(){
        List<FatoDb> facts = new ArrayList<>();
        facts.add(new FatoDb("Smartphone"));
        facts.add(new FatoDb("Celular para Jogos"));
        facts.add(new FatoDb("8GB RAM+"));
        facts.add(new FatoDb("DualChip"));
        facts.add(new FatoDb("4g"));
        facts.add(new FatoDb("5g"));
        facts.add(new FatoDb("Melhor Camera"));
        facts.add(new FatoDb("Reconhecimento Facial"));
        facts.add(new FatoDb("4K Videos"));
        facts.add(new FatoDb("Full HD"));
        facts.add(new FatoDb("Bateria de 4200 mAh"));
        facts.add(new FatoDb("Protecao contra agua"));
        facts.add(new FatoDb("Cartao de memoria"));
        facts.add(new FatoDb("Budget"));
        facts.add(new FatoDb("IOS"));

        for(FatoDb fato : facts){
            dbF.persist(fato);
        }

    }

}
