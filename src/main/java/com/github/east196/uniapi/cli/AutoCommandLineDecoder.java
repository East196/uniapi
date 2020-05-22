package com.github.east196.uniapi.cli;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.boon.core.reflection.BeanUtils;
import org.boon.core.reflection.Reflection;
import org.boon.core.reflection.fields.FieldAccess;

import com.github.east196.uniapi.core.Response;



public class AutoCommandLineDecoder implements CommandLineDecoder {

	@Override
	public <T> T decode(CommandLine commandLine, Class<T> classType) {
		T src=Reflection.newInstance(classType);
		Map<String, FieldAccess> fieldMap = Reflection.getAllAccessorFields(classType);
		for (Entry<String, FieldAccess> fieldEntry : fieldMap.entrySet()) {
			String name=fieldEntry.getKey();
			System.out.println(fieldEntry.getValue());
			if(commandLine.hasOption(name)){
				BeanUtils.injectIntoProperty(src, name, commandLine.getOptionValue(name));
			}
		}
		return src;
	}
	
	public static void main(String[] args) throws ParseException {
		String[] params={"--code","1","--message","???asda大"};
		CommandLineParser parser = new PosixParser();
		CommandLine commandLine = parser.parse(getOptions(), params);
		Response r=new AutoCommandLineDecoder().decode(commandLine, Response.class);
		System.out.println(r);
	}
	
	public static Options getOptions() {
		Options opt = new Options();
		opt.addOption("c", "code", true, "代码");
		opt.addOption("m", "message", true, "名称");
		return opt;
	}

}
