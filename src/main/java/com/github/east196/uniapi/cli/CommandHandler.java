package com.github.east196.uniapi.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.east196.uniapi.core.InnerHandler;
import com.github.east196.uniapi.core.InnerHandlers;
import com.github.east196.uniapi.core.Responses;



public class CommandHandler<In, Out> {

	private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

	public static void main(String[] args) {
		new CommandHandler<>().handle(args);
	}

	public void handle(String[] args) {
		Options opt = getOptions();
		String usage = "cmd [-h][-n/--cmd-name][-cn/--city-name]";
		HelpFormatter formatter = new HelpFormatter();
		try {
			CommandLineParser parser = new PosixParser();
			CommandLine commandLine = parser.parse(opt, args);
			if (!commandLine.hasOption("n")) {
				logger.debug("this command {} no cmd-name!", commandLine);
				throw new ParseException("no cmd-name!");
			}

			InnerHandler<In, Out> innerHandler = InnerHandlers.getHandler();
			In in = new AutoCommandLineDecoder().decode(commandLine, innerHandler.getInClass());
			Out commandResult = innerHandler.handle(in);
			logger.debug("this command {} result is {}", commandLine, commandResult);
			// 如果包含有-h或--help，则打印出帮助信息
			if (commandLine.hasOption("h")) {
				HelpFormatter hf = new HelpFormatter();
				hf.printHelp(usage, "--------------------------------------------", opt,
						"--------------------------------------------");
			}
			if (!commandResult.equals(Responses.OK_CODE)) {
				formatter.printHelp(usage, "the cmd is error,please see help", opt, commandResult + "\nend help"); // 如果发生异常，则打印出帮助信息
			}
		} catch (ParseException e) {
			formatter.printHelp(usage, "the cmd is error,please see help", opt, "end help"); // 如果发生异常，则打印出帮助信息
		}
	}

	public static Options getOptions() {
		Options opt = new Options();
		opt.addOption("h", "help", false, "显示帮助信息");
		opt.addOption("n", "cmd-name", true, "命令名称");
		opt.addOption("cn", "city-name", true, "城市名称");
		return opt;
	}

}
