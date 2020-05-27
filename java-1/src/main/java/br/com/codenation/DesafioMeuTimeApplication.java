package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	protected final List<Time> times = new ArrayList<>();
	protected  List<Jogador> jogadores = new ArrayList<>();


	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
		if(existeTime(id)) throw new IdentificadorUtilizadoException("Identificador já utilizado");
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}


	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (!existeTime(idTime)) throw new TimeNaoEncontradoException("Time não encontrado");
		if (existeJogador(id)) throw new IdentificadorUtilizadoException("Identificador já utilizado");
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador j = getJogador(idJogador);
		times.forEach(t->{
			if (t.getId().equals(j.getIdTime()))
				t.setIdCapitao(j.getId());
		});
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time t = getTime(idTime);
		if (t.getIdCapitao()==null) throw new CapitaoNaoInformadoException("Capitao nao informado");
		return t.getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return getJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return getTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return getJogadores(idTime).map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return getJogadores(idTime).sorted(Comparator.comparingLong(Jogador::getNivelHabilidade).reversed())
				.map(Jogador::getId).findFirst().get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return getJogadores(idTime).sorted(Comparator.comparing(Jogador::getDataNascimento).thenComparingLong(Jogador::getId)).map(Jogador::getId).findFirst().get();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream().sorted(Comparator.comparingLong(Time::getId)).map(Time::getId).collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return getJogadores(idTime).sorted(Comparator.comparing(Jogador::getSalario)
				.reversed().thenComparingLong(Jogador::getId)).map(Jogador::getId).findFirst().get();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return getJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores.stream().sorted(Comparator.comparingInt(Jogador::getNivelHabilidade)
				.reversed().thenComparingLong(Jogador::getId)).limit(top.longValue())
				.map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		//if (!existeTime(timeDaCasa) | !existeTime(timeDeFora)) throw new TimeNaoEncontradoException("Time nao encontrado");
		Time timeCasa = getTime(timeDaCasa);
		Time timeFora = getTime(timeDeFora);

		if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
			return timeFora.getCorUniformeSecundario();
		else return timeFora.getCorUniformePrincipal();
	}


	private boolean existeTime(Long id) {
		return times.stream().anyMatch(time -> time.getId().equals(id));
	}

	private Time getTime(Long idTime) throws TimeNaoEncontradoException {
		return times.stream().filter(time -> time.getId().equals(idTime)).findFirst().orElseThrow(() -> new TimeNaoEncontradoException("Time de id: " + idTime + " não encontrado"));
	}

	private boolean existeJogador(Long id) {
		return jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id));
	}

	private Jogador getJogador(Long id) throws JogadorNaoEncontradoException {
		return jogadores.stream().filter(j -> j.getId().equals(id)).findFirst()
				.orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
	}

	private Stream<Jogador> getJogadores(Long idTime) throws TimeNaoEncontradoException{
		if (!existeTime(idTime)) throw new TimeNaoEncontradoException("Time não encontrado!");
		return jogadores.stream().filter(j->j.getIdTime().equals(idTime));
	}


}
