package DM1;

public class ConsoleLogger extends AbstractLogger {
 
   public ConsoleLogger(int level){
      this.level = level;
   }


   @Override
   protected void write(String message) {
      //解析   info  : 日志配置格式：
      System.out.println("Standard Console info::Logger: " + message);
   }
}