import javafx.util.Pair;

import java.util.*;

public class Resolver {
    private Map<String, List<String>> rulesHash;
    private List<String> facts;
    private List<String> leftList;
    private List<String> falsifieds;
    private List<Pair<String, String>> explicacoes;
    private Scanner read = new Scanner(System.in);
    private String r;

    public List<Pair<String, String>> getExplicacoes() {
        return explicacoes;
    }

    public void setExplicacoes(List<Pair<String, String>> explicacoes) {
        this.explicacoes = explicacoes;
    }


    public List<String> getVariables() {
        List<String> retorno = new ArrayList<>();

        for (String left : leftList) {
            if (left.contains("&")) {

                String[] splited = left.split("&");

                for (int i = 0; i < splited.length; i++) {

                    if (!retorno.contains(splited[i].trim())) {
                        retorno.add(splited[i].trim());
                    }
                }
            } else {
                if (!retorno.contains(left.trim())) {
                    retorno.add(left.trim());
                }
            }
        }

        Iterator it = rulesHash.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            List<String> cons = (List<String>) pair.getValue();

            for (String right : cons) {
                if (!retorno.contains(right)) {
                    retorno.add(right);
                }
            }
        }

        return retorno;
    }

    public Resolver(Map<String, List<String>> rulesHash, List<String> facts) {
        this.rulesHash = rulesHash;
        this.facts = facts;
        this.explicacoes = new ArrayList<>();
        this.leftList = makeLeft();
        this.falsifieds = new ArrayList<String>();
    }


    private List<String> makeLeft() {

        List<String> lefts = new ArrayList<>();

        Iterator it = rulesHash.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            lefts.add(pair.getKey().toString().trim());
        }

        return lefts;
    }

    public List<String> forwardResult() {

        while (true) {
            int c = 0;

            for (String string : leftList) {

                String[] splited = string.split("&");

                if (splited.length > 1) {
                    int qtd = splited.length;
                    int count = 0;

                    for (int i = 0; i < splited.length; i++) {
                        if (facts.contains(splited[i].trim())) {
                            count++;
                        }
                    }

                    if (count == qtd) {
                        for (String right : rulesHash.get(string)) {
                            if (!facts.contains(right)) {
                                facts.add(right);
                                c++;
                            }
                        }
                    }
                } else if (facts.contains(string)) {
                    for (String right : rulesHash.get(string)) {
                        if (!facts.contains(right)) {
                            facts.add(right);
                            c++;
                        }
                    }
                }
            }

            if (c == 0) {
                break;
            }
        }

        return facts;
    }



    public String backwardResult(String goal) {

        Stack<String> toProve = new Stack<String>();

        toProve.push(goal);

        while (!toProve.isEmpty()) {
            if (facts.contains(goal)) {
                return "verdade";
            }

            if (canIProveIt(toProve.peek())) {

                String newFact = toProve.pop();
                addNewFacts(newFact);

            } else {
                Stack<String> newStack = addInStack(toProve.peek(), toProve);

                if (toProve.size() == newStack.size()) {
                    String stackTop = toProve.pop();

                    System.out.println("A variável: " + stackTop + " é verdade? (s/n)");
                    r = read.next();

                    boolean isTrue = isRTrue(r);

                    if (isTrue) {
                        addNewFacts(stackTop);
                    } else {
                        falsifieds.add(stackTop);
                    }

                } else {
                    toProve = newStack;
                }
            }
        }

        if (toProve.isEmpty() && facts.contains(goal)) {
            return "verdade";
        }

        return "indefinido";
    }

    private boolean isRTrue(String r){
        if(r.equals("s") || r.equals("s"))
            return true;
        else
            return false;
    }

    private boolean canIProveIt(String toProve) {

        if (facts.contains(toProve)) {
            return true;
        } else {
            for (String string : facts) {
                if (rulesHash.containsKey(string) && rulesHash.get(string).contains(toProve)) {
                    return true;
                }
            }
        }

        return false;

    }

    private void addNewFacts(String fact) {

        if (!facts.contains(fact)) {
            facts.add(fact);
        }

        if (rulesHash.containsKey(fact)) {
            for (String string : rulesHash.get(fact)) {
                if (!facts.contains(string)) {
                    facts.add(string);
                }
            }
        }
    }

    private Stack<String> addInStack(String stackTop, Stack<String> stack) {

        for (String left : leftList) {
            if (!falsifieds.contains(left) && rulesHash.get(left).contains(stackTop)) {
                stack.push(left);
            }
        }

        return stack;
    }
}
