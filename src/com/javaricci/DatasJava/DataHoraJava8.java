package com.javaricci.DatasJava;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DataHoraJava8 {

	public static void main(String[] args) {
		
		// ## NÃO EXISTE CONSTRUTOR NATIVO PARA NENHUMA DESSAS CLASSES DE DATA ###

		// LocalDate - Representa uma data: 30/06/2023
		LocalDate ldNow = LocalDate.now();
		System.out.println("Resultado LocalDate ldNow: "+ldNow);//2023-06-30
		LocalDate.of(2023, 6, 30);
		LocalDate ldamd = LocalDate.of(2023, 6, 30);
		System.out.println("Resultado LocalDate ldamd: "+ldamd);//2023-06-30
		LocalDate ldamd1 = LocalDate.of(2023, Month.AUGUST, 30);
		System.out.println("Resultado LocalDate ldamd1: "+ldamd1);//2023-08-30
		LocalDate ld2 = ldamd.plusWeeks(2).plusDays(1).minusMonths(1);
		System.out.println("Resultado LocalDate ld2: "+ld2);//2023-06-15


		// LocalTime - Representa uma hora: 14:17:00
		LocalTime ltNow = LocalTime.now();
		System.out.println("Resultado LocalTime ltNow: "+ltNow);//17:18:38.511445600
		//LocalTime.of(hour, minute, second, nanoOfSecond);
		LocalTime lt1 = LocalTime.of(14, 17, 12, 100000);
		System.out.println("Resultado LocalTime lt1: "+lt1);//14:17:12.000100


		// LocalDateTime - Representa data+hora: 30/06/2023 14:17:00
		LocalDateTime ldtNow = LocalDateTime.now();
		System.out.println("Resultado LocalDateTime ldtNow: "+ldtNow);//2023-06-30T17:18:38.511445600
		LocalDateTime ldt = LocalDateTime.of(ldamd,lt1);
		System.out.println("Resultado LocalDateTime ldt: "+ldt);//2023-06-30T14:17:12.000100


		// Instant - Representa um instante/momente na linha do tempo (millissegundos a partir de 01/01/1970 a 00:00:00)
		// Instant Não foi criado para trabalhar com operacões matemáticas com DATAS.
		Instant iNow = Instant.now();
		System.out.println("Resultado Instant iNow: "+iNow); //2023-06-30T20:18:38.512442600Z
		Instant i = Instant.ofEpochMilli(500000000000L);
		System.out.println("Resultado Instant i: "+i); //1985-11-05T00:53:20Z


		// CLASSE MAIS COMPLETA E ATUAL DO JAVA ZonedDateTime - LocalDateTime com TimeZone (FUSO HORÁRIO)
		ZonedDateTime zdtNow = ZonedDateTime.now();
		System.out.println("Resultado ZonedDateTime zdtNow: "+zdtNow);//2023-06-30T16:26:08.455576400-03:00[America/Sao_Paulo]
		ZoneId zoneId = ZoneId.of("America/Montevideo");
		ZonedDateTime zdt = ZonedDateTime.of(ldt, zoneId);
		System.out.println("Resultado ZonedDateTime zdt: "+zdt);//2023-06-30T14:17:12.000100-03:00[America/Montevideo]
		
		ZonedDateTime zdtAgoraOperacaoPlus = ZonedDateTime.now();
		ZonedDateTime zdtSomaDias = zdtAgoraOperacaoPlus.plusDays(31);
		System.out.println("Resultado Data + 31 DIAS ZonedDateTime plusDays(): "+zdtSomaDias);//2023-07-31T14:17:12.000100-03:00[America/Montevideo]
		ZonedDateTime zdtSomaMeses = zdtAgoraOperacaoPlus.plusMonths(12);
		System.out.println("Resultado Data + 12 MESES ZonedDateTime plusMonths(): "+zdtSomaMeses);//2024-06-30T14:17:12.000100-03:00[America/Montevideo]
		ZonedDateTime zdtSomaAnos = zdtAgoraOperacaoPlus.plusYears(2);
		System.out.println("Resultado Data + 2 ANOS ZonedDateTime plusYears(): "+zdtSomaAnos);//2025-06-30T14:17:12.000100-03:00[America/Montevideo]
		
		ZonedDateTime zdtAgoraOperacaoMinus = ZonedDateTime.now();
		ZonedDateTime zdtSubtracaoDias = zdtAgoraOperacaoMinus.minusDays(31);
		System.out.println("Resultado Data - 31 DIAS ZonedDateTime minusDays(): "+zdtSubtracaoDias);//2023-07-31T14:17:12.000100-03:00[America/Montevideo]
		ZonedDateTime zdtSubtracaoMeses = zdtAgoraOperacaoMinus.minusMonths(12);
		System.out.println("Resultado Data - 12 MESES ZonedDateTime minusMonths(): "+zdtSubtracaoMeses);//2024-06-30T14:17:12.000100-03:00[America/Montevideo]
		ZonedDateTime zdtSubtracaoAnos = zdtAgoraOperacaoMinus.minusYears(2);
		System.out.println("Resultado Data - 2 ANOS ZonedDateTime minusYears(): "+zdtSubtracaoAnos);//2025-06-30T14:17:12.000100-03:00[America/Montevideo]
		
		DayOfWeek zdtDiaSemana = 	zdtNow.getDayOfWeek();
		int zdtAno = zdtNow.getYear();
		int zdtMes = zdtNow.getMonthValue();
		int zdtDia = zdtNow.getDayOfMonth();
		
		System.out.println("Resultado Data - ZonedDateTime Dia Semana: "+zdtDiaSemana);
		System.out.println("Resultado Data - ZonedDateTime Ano: "+zdtAno);
		System.out.println("Resultado Data - ZonedDateTime Mes: "+zdtMes);
		System.out.println("Resultado Data - ZonedDateTime Dia: "+zdtDia);
		
		//FORMATAR A DATA EM DIA/MÊS/ANO de ZonedDateTime
		DateTimeFormatter dtfzdt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDatezdt = dtfzdt.format(zdtNow);
		System.out.println("Resultado da Data Formatada ZonedDateTime: "+formattedDatezdt);//23/07/2023


		//Exemplo 01 usando ChronoUnit
		LocalDate dtInicial = LocalDate.of(2023, 1, 1);
		LocalDate dtAgora = LocalDate.of(2024, 6, 30);
		long qtdDias = ChronoUnit.DAYS.between(dtInicial, dtAgora);
		long qtdDias1 = qtdDias - 46;
		System.out.println("Resultado QTD Dias LocalDate ChronoUnit: "+qtdDias);//546
		System.out.println("Resultado QTD Dias - 46 LocalDate ChronoUnit: "+qtdDias1);//546 - 46 = 500
		long qtdMeses = ChronoUnit.MONTHS.between(dtInicial, dtAgora);
		System.out.println("Resultado QTD Meses LocalDate ChronoUnit: "+qtdMeses);//17
		long qtdAnos = ChronoUnit.YEARS.between(dtInicial, dtAgora);
		System.out.println("Resultado QTD Anos LocalDate ChronoUnit: "+qtdAnos);//1
		
		LocalDate dtInicialSomaDias = dtInicial.plusDays(31);
		System.out.println("Resultado Data + 31 DIAS LocalDate ChronoUnit: "+dtInicialSomaDias);//2023-02-01
		LocalDate dtInicialSomaMeses = dtInicial.plusMonths(12);
		System.out.println("Resultado Data + 12 MESES LocalDate ChronoUnit: "+dtInicialSomaMeses);//2024-01-01
		LocalDate dtInicialSomaAnos = dtInicial.plusYears(2);
		System.out.println("Resultado Data + 2 ANOS LocalDate ChronoUnit: "+dtInicialSomaAnos);//2025-02-01

		LocalDate dtInicialSubtraiDias = dtInicial.minusDays(31);
		System.out.println("Resultado Data - 31 DIAS LocalDate ChronoUnit: "+dtInicialSubtraiDias);//2022-12-01
		LocalDate dtInicialSubtraiMeses = dtInicial.minusMonths(12);
		System.out.println("Resultado Data - 12 MESES LocalDate ChronoUnit: "+dtInicialSubtraiMeses);//2022-01-01
		LocalDate dtInicialSubtraiAnos = dtInicial.minusYears(2);
		System.out.println("Resultado Data - 2 ANOS LocalDate ChronoUnit: "+dtInicialSubtraiAnos);//2021-01-01


		//FORMATAR A DATA EM DIA/MÊS/ANO
		LocalDate dataAgora = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = dtf.format(dataAgora);
		System.out.println("Resultado da Data Formatada: "+formattedDate);//30/06/2023
		
		Locale locale = new Locale("pt", "BR");
		DateTimeFormatter dtfDescricao = DateTimeFormatter.ofPattern("dd/MMMM/yyyy",locale);
		String formattedDateDescMes = dtfDescricao.format(dataAgora);
		System.out.println("Resultado da Data Formatada Extenso Mês: "+formattedDateDescMes);//30/junho/2023
		
		//DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MMM-uuuu", locale);
		//LocalDate date1 = LocalDate.parse("28-set-2018", parser);
		//System.out.println("Converte a Data 28-set-2018 para 2018-09-28: "+date1);//2018-09-28


		//Exemplo 01 usando Duration
		LocalDateTime dtInicial_ldt = LocalDateTime.of(2023, 1, 1, 9, 0);
		LocalDateTime dtAgora_ldt = LocalDateTime.of(2023, 6, 30, 9, 0);
		Duration qtdDiasDuration = Duration.between(dtInicial_ldt, dtAgora_ldt);
		System.out.println("Resultado LocalDateTime Usando Duration: "+qtdDiasDuration.toDays());//180


	}

}
