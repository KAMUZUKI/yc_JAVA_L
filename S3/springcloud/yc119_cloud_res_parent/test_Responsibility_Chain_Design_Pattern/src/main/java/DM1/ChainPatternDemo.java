package DM1;

/**
 * 测试类
 */
public class ChainPatternDemo {

   /**
    * #logging:
    * #  level:
    * #    root: error          errorLogger 3->debugLogger 2 -> infoLogger 1
    * #    com.yc: debug      errorLogger  3  ->debugLogger  2     log.debug  2("hello")
    * #    org.springframework: info    errorLogger 3->debugLogger 2 -> infoLogger 1
    * 解析日志配置:
    * 组装责任链
    * @return
    */
   private static AbstractLogger getChainOfLoggers(){
      //创建日志器,并指定每个日志器的能处理级别
      AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);  //1
      AbstractLogger debugLogger = new FileLogger(AbstractLogger.DEBUG);   //2
      AbstractLogger infoLogger = new ConsoleLogger(AbstractLogger.INFO);  //3
      //组装职责链
      // info 3> debug 2> error 1
      errorLogger.setNextLogger(debugLogger);
      debugLogger.setNextLogger(infoLogger);
      return errorLogger;   //3
   }


   public static void main(String[] args) {
      //获取日志职责链
      AbstractLogger loggerChain = getChainOfLoggers();
      // errorLogger 1->debugLogger 2 -> infoLogger 3
      //                                  1
     loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

     // com.yc: debug      errorLogger  3  ->debugLogger  2
      // log.error  3("hello")
      //log.info();                    //2
   //loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");  // log.debug(   "this is a debug level infomation");
 
      //loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");  //log.error("");  1
   }
}