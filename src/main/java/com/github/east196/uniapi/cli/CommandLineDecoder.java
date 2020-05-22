package com.github.east196.uniapi.cli;

import org.apache.commons.cli.CommandLine;

public interface CommandLineDecoder {
	
	public <T> T decode(CommandLine commandLine,Class<T> classType);

}
