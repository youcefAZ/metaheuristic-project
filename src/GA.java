import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;

public class GA extends Recherche {

    int nbIndividuals, nbGenerations;
    float mutationRate, crossoverRate;
    LinkedList<individual> population = new LinkedList<>();

    public GA(String[][] data, int variableLength, int nbC, int nbIndividuals, int nbGenerations, float mutationRate, float crossoverRate) {
        super(data, variableLength, nbC);
        this.nbIndividuals = nbIndividuals;     //taille de la population
        this.nbGenerations = nbGenerations;     // nombre de générations
        this.mutationRate = mutationRate;       // taux de mutation (nombre de gènes à muter)
        this.crossoverRate = crossoverRate;     // taux de crossover
    }

    public ReturnClass GeneticAlgorithm() {

        //Générer une population de façon aléatoire
        int generation = 1;
        for (int i = 0; i < nbIndividuals; i++) {
            individual individu = new individual(variableLength);
            individu.InitialiseIndividual(generation);
            individu.fitness = testAstar(individu.variable);
            addToPopulation(population, individu);
        }

        /**/
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Meilleur individu à l'iterratio 1 :");
        System.out.println("fitness x1 = " + population.get(0).fitness + ", Generation : " + population.get(0).generation);

        LinkedList<individual> UpdatePopulation = new LinkedList<individual>();
        int nbParents = percentageToNumber(crossoverRate, nbIndividuals);

        while (generation < nbGenerations) {

            //Selection des parents puis croisement entre parents pour générer des offsprings (children)
            for (int i = 0; i < nbParents; i++) {
                individual parent1 = population.get(i);
                individual parent2 = population.get(i + 1);

                ArrayList<individual> children = crossover(parent1, parent2);

                //appliquer la mutation au 1er offspring
                individual ch1 = children.get(0);
                ch1 = mutation((int) mutationRate, ch1);
                ch1.fitness = testAstar(ch1.variable);
                ch1.generation = generation + 1;
                addToPopulation(UpdatePopulation, ch1);

                //appliquer la mutation au 2eme offspring
                individual ch2 = children.get(1);
                ch2 = mutation((int) mutationRate, ch2);
                ch2.fitness = testAstar(ch2.variable);
                ch2.generation = generation + 1;
                addToPopulation(UpdatePopulation, ch2);

                i = i + 1;
            }

            //'Updatepopulation' contient jusqu'à maintenant les nouveaux enfants issus du croisement des meilleurs parents
            //On complète cette nouvelle population avec les meilleurs individus de la génération précédente (parents)
            int NbRest = population.size() - UpdatePopulation.size();
            for (int i = 0; i < NbRest; i++) {
                addToPopulation(UpdatePopulation, population.get(i));
            }

            population = UpdatePopulation;
            generation = generation + 1;

            /**/
            System.out.println("\n--------------------------------------------------------------------");
            System.out.println("Meilleur individu à l'iterratio " + generation + " :");
            System.out.println("fitness = " + population.get(0).fitness + ", Generation : " + population.get(0).generation);

        }

        boolean satisfied = testAveugle(population.get(0).variable);
        ReturnClass returnClass = new ReturnClass(population.get(0).variable, population.get(0).fitness, satisfied);

        return returnClass;
    }

    private ArrayList<individual> crossover(individual parent1, individual parent2) {
        int[] p1 = parent1.variable;
        int[] p2 = parent2.variable;

        ArrayList<individual> Children = new ArrayList<individual>();
        int crossoverPoint = (int) Math.round(p1.length / 2);
        //System.out.println("crossoverPoint = " + crossoverPoint);

        int[] ch1 = new int[p1.length];
        int[] ch2 = new int[p1.length];

        for (int i = 0; i < crossoverPoint; i++) {
            ch1[i] = p1[i];
            ch2[i] = p2[i];
        }
        for (int i = crossoverPoint; i < p1.length; i++) {
            ch1[i] = p2[i];
            ch2[i] = p1[i];
        }

        individual child1 = new individual(p1.length);
        child1.variable = ch1;
        Children.add(child1);

        individual child2 = new individual(p1.length);
        child2.variable = ch2;
        Children.add(child2);


        return Children;
    }

    private individual mutation(int mutationRate, individual individu) {
        individual newIndividu = new individual(individu.variableLength);
        Random random = new Random();
        int[] newVar = individu.variable;
        if (random.nextInt(100) <= mutationRate) {
            int j = random.nextInt(newVar.length);  // indice du gène à muter, généré aléatoirement.
            if (newVar[j] == 1) {
                newVar[j] = 0;
            } else if (newVar[j] == 0) {
                newVar[j] = 1;
            }
        }

        newIndividu.variable = newVar;
        newIndividu.fitness = testAstar(newVar);
        return newIndividu;

    }

    //ajouter un individu à la population en assurant un ordre décroissant selon la valeur de fitness function
    private void addToPopulation(LinkedList<individual> NewPopulation, individual individu) {
        boolean bool = false;
        int i = 0;
        while (i < NewPopulation.size() && !bool) {
            if ((NewPopulation.get(i)).fitness < individu.fitness) {
                bool = true;
            } else {
                i++;
            }
        }
        NewPopulation.add(i, individu);
    }

    //Convertir un pourcentage en nombre d'individus
    private int percentageToNumber(float rate, int nb) {
        int intRate = Math.round(((rate * nb) / 100));
        if (intRate % 2 != 0) {
            intRate = intRate + 1;
        }
        return intRate;
    }



}