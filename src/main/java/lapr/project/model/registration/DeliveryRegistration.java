package lapr.project.model.registration;

import javafx.util.Pair;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Address;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class DeliveryRegistration {

    private AdjacencyMatrixGraph<Address, Double> m_oGraph = new AdjacencyMatrixGraph<>();


    public double getShortestPath(Address startingPoint, Address destiny) {

        return 0.0;
    }

    /**
     * Este método verifica se o Address existe
     *
     * @param a Address a verificar
     * @return true caso exista, false caso não exista
     */
    public boolean verifyAddress(Address a) {
        for (Address Address : this.m_oGraph.vertices()) {
            if (a.equals(Address))
                return true;
        }
        return false;
    }

    /**
     * Exercício 6
     * <p>
     * Este método começa por calcular as n cidades intermédias onde cada utilizador tem o maior número
     * de amigos
     * (Este passo é efectuado com auxílio do método getMiddleCities)
     * <p>
     * De seguida, são geradas todas as permutações possíveis para as cidades intermédias
     * (Este passo é efectuado com auxílio do método generatePerm)
     * <p>
     * De seguida, são calculados os menores caminhos de todas as permutações previamente calculadas
     * (Este passo é efectuado com auxílio do método calculatePermutaionpaths)
     * <p>
     * De seguida, verifica-se qual dos caminhos calculados é o mais curto, retornando um
     * Pair<List<String>, Double>, contendo o menor caminho(Key) e a sua distância(Value)
     *
     * @param addressA Primeiro utilizador a ser verificado
     * @param addressB Segundo utilizador a ser verificado
     * @param n        Número de cidades intermédias
     * @return menor caminho e a sua distância
     */
    public Pair<List<String>, Double> shortestPathBetweenAddresss(Address addressA, Address addressB) {                      //O(k^2*V^2)
        if (!verifyAddress(addressA) || !verifyAddress(addressB) || addressA.equals(addressB))
            return new Pair<>(new LinkedList<>(), 0.0);
        LinkedList<String> middleCities = getMiddleCities(addressA, addressB);                                             //O(V)
        List<LinkedList<String>> permutations = generatePerm(middleCities);                                             //O(n!)
        List<Pair<LinkedList<String>, Double>> listOfPaths = calculatePermutationPaths(addressA, addressB, permutations);     //O(k^2*V^2)
        if (listOfPaths.isEmpty())
            return new Pair<>(new LinkedList<>(), 0.0);
        Double cost = Double.MAX_VALUE;
        LinkedList<String> finalResult = new LinkedList<>();
        for (Pair<LinkedList<String>, Double> list : listOfPaths) {                                                       //O(n)
            Double auxCost = list.getValue();
            if (auxCost < cost) {
                cost = auxCost;
                finalResult = list.getKey();
            }
        }
        return new Pair<>(finalResult, cost);
    }

    /**
     * Este método calcula as cidades intermédias para o AddressA e AddressB
     * (Este passo é efectuado com utensílio ao método citiesHighestFriendsByAddress)
     *
     * @param AddressA Primeiro utilizador a ser verificado
     * @param AddressB Segundo utilizador a ser verificado
     * @param n        número de cidades intermédias
     * @return cidades intermédias entre os 2 utilizadores
     */
    public LinkedList<String> getMiddleCities(Address addressA, Address addressB) {                                          //O(V)
        LinkedList<String> middleCities = new LinkedList<>();
        for (String str : citiesHighestFriendsByAddress(    AddressA, n)) {                                                        //O(V)
            if (!middleCities.contains(str) && !str.equals(addressA) && !str.equals(addressB)) {
                middleCities.add(str);
            }
        }
        for (String str : citiesHighestFriendsByAddress(AddressB, n)) {                                                        //O(V)
            if (!middleCities.contains(str) && !str.equals(AddressA.getCity()) && !str.equals(AddressB.getCity())) {
                middleCities.add(str);
            }
        }
        return middleCities;
    }

    /**
     * Método recursivo que gera todas as permutações possíveis entre as cidades intermédias
     * previamente calculadas
     * <p>
     * https://stackoverflow.com/questions/10305153/generating-all-possible-permutations-of-a-list-recursively
     * Foi utilizada esta resolução para calcular todas as permutações entre as cidades intermédias
     *
     * @param original Lista com cidades intermédias
     * @return Permutações entre cidades intermédias
     */
    public List<LinkedList<String>> generatePerm(List<String> original) {                                               //O(n!)
        if (original.isEmpty()) {
            List<LinkedList<String>> result = new ArrayList<>();
            result.add(new LinkedList<>());
            return result;
        }
        String firstElement = original.remove(0);
        List<LinkedList<String>> returnValue = new ArrayList<>();
        List<LinkedList<String>> permutations = generatePerm(original);
        for (List<String> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                LinkedList<String> temp = new LinkedList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    /**
     * Este método calcula as n cidades com mais amigos do utilizador u
     * (Este passo é executado com auxílio ao método friendsByCity)
     *
     * @param u Utilizador a ser verificado
     * @param n Número de cidades
     * @return Cidades com mais amigos do utilizador u
     */
    public List<Address> citiesHighestFriendsByAddress(Address u, Integer n) {                                                 //O(V) ou O(n)
        List<Address> result = new ArrayList<>();
        Map<String, List<Address>> m = checkConnectionAux(u);                                                                   //O(V)
        Iterator<String> itr2 = m.keySet().iterator();
        int i = 0;
        while (itr2.hasNext() && i < n) {                                                                                //O(n)
            String city = itr2.next();
            if (!city.equals(u.getCity())) {
                result.add(city);
                i++;
            }
        }
        return result;
    }

    /**
     * Este método calcula todos os shortestPaths para todas as possíveis permutações entre
     * as cidades intermédias
     * (Este passo é executado com auxílio ao algoritmo shortestPath)
     *
     * @param AddressA     Primeiro utilizador para ser verificado
     * @param AddressB     Segundo utilizador para ser verificado
     * @param permutations Lista com todas as permutações entre as cidades intermédias
     * @return Lista com todos os caminhos mínimos possíveis das permutações
     */
    public List<Pair<LinkedList<String>, Double>> calculatePermutationPaths(Address AddressA, Address AddressB,
                                                                            List<LinkedList<String>> permutations) {    //O(k^2*V^2)
        List<Pair<LinkedList<String>, Double>> listOfPaths = new LinkedList<>();
        String start = AddressA.getCity(), finish = AddressB.getCity();
        for (LinkedList<String> list : permutations) {                                                                   //O(k^2*V^2)
            list.addFirst(start);
            list.addLast(finish);
            LinkedList<String> permutationPath = new LinkedList<>();
            Double totalCost = 0.0;
            for (int i = 0; i < list.size() - 1; i++) {                                                                  //O(k*V^2)
                LinkedList<String> tempPath = new LinkedList<>();
                totalCost += GraphAlgorithms.shortestPath(this.countryNetwork, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
                if (tempPath.size() == 0)
                    return new LinkedList<>();
                tempPath.removeLast();
                permutationPath.addAll(tempPath);
            }
            permutationPath.add(finish);
            listOfPaths.add(new Pair<>(permutationPath, totalCost));
        }
        return listOfPaths;
    }

    /**
     * Este método retorna um Map<String, List<Address>> com uma cidade e os amigos do Address u
     * pertencentes a esta.
     * <p>
     * É de notar que o map é ordenado(decrescentemente) consoante o tamanho da lista de amigos
     *
     * @param u Utilizador a ser verificado
     * @return Amigos do Address u por cidade
     */
    public Map<String, List<Address>> friendsByCity(Address u) {                                                              //O(V)
        Map<String, List<Address>> m = new TreeMap<>();
        if (m_oGraph.inDegree(u) == 0 || m_oGraph.inDegree(u) == -1)
            return new LinkedHashMap<>();
        for (Address friend : m_oGraph.directConnections(u)) {                                                     //O(V)
            if (!m.containsKey(friend.getCity()))
                m.put(friend.getCity(), new ArrayList<>());
            m.get(friend.getCity()).add(friend);
        }
        Map<String, List<Address>> sorted = m.entrySet().stream()                                                          //O(V)
                .sorted(Collections.reverseOrder(comparingInt(e -> e.getValue().size())))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        return sorted;
    }

    /**
     * Auxiliar and recursive method that adds all the Addresss that are connected.
     */
    private HashSet<Address> checkConnectionAux(HashSet<Address> aux) {
        int previousSizeAux;
        if (aux.size() >= getAddressWithRelations().size()) {
            return aux;
        }
        previousSizeAux = aux.size();
        addAdjacentAddress(aux);
        if (aux.size() == previousSizeAux) {
            return null;
        }
        return checkConnectionAux(aux);
    }

    /**
     * Adds the Addresss connected to a determined Address.
     */
    private void addAdjacentAddress(HashSet<Address> list) {
        List<Address> listAdjAddresss = new ArrayList<>();
        for (Address a : list) {
            for (Address adj : m_oGraph.adjVertices(a)) {
                if (!(listAdjAddresss.contains(adj) || list.contains(adj))) {
                    listAdjAddresss.add(adj);
                }
            }
        }
        list.addAll(listAdjAddresss);
    }

    /**
     * Returns a List of Addresss that have at least one connection.
     */

    private List<Address> getAddressWithRelations() {
        List<Address> lst = new ArrayList<>();
        for (Address a : m_oGraph.vertices()) {
            if (!(m_oGraph.inDegree(a) == -1)) {
                lst.add(a);
            }
        }
        return lst;
    }

}