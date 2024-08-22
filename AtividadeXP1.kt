//Imports//////////////////
import kotlin.random.Random
///////////////////////////

//Classes/////////////////////////////////////////////////////////////////////////////////////////////////
//Classe usada no exercicio 4
class Aluno(val nome: String, val notas: DoubleArray) {

    fun calcularMedia(): Double {
        return notas.average()
    }

    fun foiAprovado(): Boolean {
        return calcularMedia() >= 7.0
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//Classe usada no exercicio 7
class Voo(val numeroDoVoo: Int, val assentosDisponiveis: IntArray) {

    fun reservarAssento(assento: Int): Boolean {
        return if (assento in assentosDisponiveis) {
            val indice = assentosDisponiveis.indexOf(assento)
            assentosDisponiveis[indice] = -1 
            true
        } else {
            false
        }
    }

    fun verificarDisponibilidade(assento: Int): Boolean {
        return assento in assentosDisponiveis && assento != -1
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//Classe usada no exercicio 8
class Produto(val nome: String, var quantidade: Int, val nivelMinimo: Int, val nivelMaximo: Int) {

    fun reporEstoque() {
        quantidade = nivelMaximo
        println("Estoque do produto '$nome' foi reposto para o nível máximo: $nivelMaximo unidades.")
    }

    fun verificarEstoque() {
        if (quantidade < nivelMinimo) {
            println("Quantidade do produto '$nome' abaixo do nível mínimo: $quantidade unidades.")
            reporEstoque()
        } else {
            println("Quantidade do produto '$nome' está adequada: $quantidade unidades.")
        }
    }
}
//Main////////////////////////////////////////////////////////////////////////////////////////////////////
fun main() {
    
    //ex1()
    //ex2()
    //ex3()
    //ex4()
    //ex5()
    //ex6()
    //ex7()
    //ex8()
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex1(){
    val x = readLine()?.toInt() ?: 0

    if(x == 2 || x == 3 || x == 5 || x == 7){
        println("$x é um número primo.")
        return
    }

    when {
        x <= 1 -> println("$x não é um número primo.")
        ((x % 2) == 0) -> println("$x não é um número primo.")
        ((x % 3) == 0) -> println("$x não é um número primo.")
        ((x % 5) == 0) -> println("$x não é um número primo.")
        ((x % 7) == 0) -> println("$x não é um número primo.")
        else -> println("$x é um número primo.")
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex2(){
    val text = readLine() ?: ""

    val count = text.count { it == ' ' }
    println("Total de espaços = $count")
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex3(){
    val alunos = mutableListOf<Aluno>()

    for (i in 1..5) {
        println("Cadastro do Aluno $i")

        print("Nome do Aluno: ")
        val nome = readLine() ?: ""

        val notas = DoubleArray(5)
        for (j in notas.indices) {
            print("Nota ${j + 1}: ")
            notas[j] = readLine()?.toDoubleOrNull() ?: 0.0
        }

        val aluno = Aluno(nome, notas)
        alunos.add(aluno)
    }

    println("\nResultados dos Alunos:")
    for (aluno in alunos) {
        val media = aluno.calcularMedia()
        val status = if (aluno.foiAprovado()) "Aprovado" else "Reprovado"
        println("${aluno.nome}: Média = %.2f, Status = $status".format(media))
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex4(){
    val alunos = Array(10) { "" }

    for (i in alunos.indices) {
        print("Digite o nome do aluno ${i + 1}: ")
        alunos[i] = readLine() ?: ""
    }

    alunos.sort()

    println("\nLista de alunos em ordem alfabética:")
    for (nome in alunos) {
        println(nome)
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex5(){
    print("Quantas vezes você deseja lançar os dados? ")
    val x = readLine()?.toIntOrNull() ?: 0

    for (i in 1..x) {
        val dado1 = Random.nextInt(1, 7)  
        val dado2 = Random.nextInt(1, 7)  
        val soma = dado1 + dado2

        println("Lançamento $i: Dado 1 = $dado1, Dado 2 = $dado2, Soma = $soma")
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex6(){
    val temperaturas = DoubleArray(30)

    for (i in temperaturas.indices) {
        print("Digite a temperatura do dia ${i + 1}: ")
        temperaturas[i] = readLine()?.toDoubleOrNull() ?: 0.0
    }

    val media = temperaturas.average()

    val maxTemp = temperaturas.maxOrNull() ?: Double.MIN_VALUE
    val minTemp = temperaturas.minOrNull() ?: Double.MAX_VALUE

    var diaMaxTemp = 0
    var diaMinTemp = 0

    for(i in temperaturas.indices) {
        if (temperaturas[i] == maxTemp){
            diaMaxTemp = i + 1
        }
        if (temperaturas[i] == minTemp){
            diaMinTemp = i + 1
        }
    }

    println("\nRelatório das Temperaturas do Mês:")
    println("Temperatura média: %.2f°C".format(media))
    println("Temperatura máxima: %.2f°C no dia $diaMaxTemp".format(maxTemp))
    println("Temperatura mínima: %.2f°C no dia $diaMinTemp".format(minTemp))
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex7(){
    val numeroDoVoo = 101
    val assentos = IntArray(10) { it + 1 }

    val voo = Voo(numeroDoVoo, assentos)

    while (true) {
        print("Digite o número do assento que deseja verificar ou 0 para sair: ")
        val assento = readLine()?.toIntOrNull() ?: 0

        if (assento == 0) break

        if (voo.verificarDisponibilidade(assento)) {
            println("Assento $assento está disponível.")
            print("Deseja reservar este assento? (s/n): ")
            val resposta = readLine()

            if (resposta?.lowercase() == "s") {
                if (voo.reservarAssento(assento)) {
                    println("Assento $assento reservado com sucesso!")
                } else {
                    println("Falha ao reservar o assento $assento.")
                }
            }
        } else {
            println("Assento $assento não está disponível.")
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ex8(){
    val produtos = mutableListOf<Produto>()

    for (i in 1..5) {
        print("Digite o nome do produto $i: ")
        val nome = readLine() ?: ""

        print("Digite a quantidade atual do produto $nome: ")
        val quantidade = readLine()?.toIntOrNull() ?: 0

        print("Digite o nível mínimo do produto $nome: ")
        val nivelMinimo = readLine()?.toIntOrNull() ?: 0

        print("Digite o nível máximo do produto $nome: ")
        val nivelMaximo = readLine()?.toIntOrNull() ?: 0

        val produto = Produto(nome, quantidade, nivelMinimo, nivelMaximo)
        produtos.add(produto)
    }

    println("\nVerificação de Estoque:")
    for (produto in produtos) {
        produto.verificarEstoque()
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////