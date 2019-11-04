package com.market.sapphires.sbt.v3.util;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import com.market.sapphires.sbt.v3.util.customtag.LoginUserTagProcessor;

public class CustomTagDialect extends AbstractProcessorDialect {

    public CustomTagDialect() {
        super("Custom Tag Dialect", "custom-tag", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new LoginUserTagProcessor(dialectPrefix));

        /*
         * HTMLの最初につけるxmlns:th="http://www.thymeleaf.org"のようなネームスペース表記を削除するために
         * org.thymeleaf.standard.processor.StandardXmlNsTagProcessorを登録する。
         */
        processors.add(
                new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));

        return processors;
    }

}
