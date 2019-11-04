package com.market.sapphires.sbt.v3.controller;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.market.sapphires.sbt.v3.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/settings")
public class SystemSettingController {

    private final MessageSource messageSource;

    @PostConstruct
    public void init() {
        MessageUtil.setMessageSource(this.messageSource);

    }

    /*
    @Autowired
    SystemSettingDao dao;
    
    private static final int MB = 1024 * 1024;
    
    @PostConstruct
    public void init() {
        MessageUtil.setMessageSource(this.messageSource);
    
        // ろぐ
        List<SystemSetting> settings = this.dao.findAll();
        if (settings.isEmpty()) {
            // TODO ありえない
            return;
        }
        SystemSetting setting = settings.get(0);
    
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        RollingFileAppender rfa = (RollingFileAppender) logger.getAppender("FILE");
        FixedWindowRollingPolicy rp = (FixedWindowRollingPolicy) rfa.getRollingPolicy();
        SizeBasedTriggeringPolicy tp = (SizeBasedTriggeringPolicy) rfa.getTriggeringPolicy();
    
        logger.setLevel(Level.valueOf(setting.getLogLevel()));
        rp.setMaxIndex(10);
        tp.setMaxFileSize(new FileSize(10 * MB));
    }
    
    @GetMapping(value = { "", "/" })
    public String info(Model model) {
        return "settings/info";
    }
    */

}
