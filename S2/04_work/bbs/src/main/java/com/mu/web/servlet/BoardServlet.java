package com.mu.web.servlet;

import com.mu.bean.Board;
import com.mu.biz.BoardBiz;
import com.mu.dao.DbHelper;
import com.mu.web.model.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "BoardServlet",value="/board.action")
public class BoardServlet extends CommonServlet {
    protected void ShowBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();

        try {
            String sql="select * from tbl_board";
            DbHelper db=new DbHelper();
            List<Board> list1 = db.select(sql, Board.class);
            sql="select  a.boardid,a.num,topicid from (select boardid, count(boardid) num,max(modifytime) lasttime from tbl_topic  group by boardid ) a,tbl_topic\n" +
                    "where a.lasttime=tbl_topic.modifytime and a.boardid=tbl_topic.boardid ";
            List<Map<String, Object>> list2= db.select(sql);
            BoardBiz bb=new BoardBiz();
            List<Board> list=bb.GetBoard(list1,list2);



            Map<Board,List<Board>> map=new HashMap<>();
            for(Board b:list){
                if(b.getParentid()==0){
                    map.put(b,new ArrayList<Board>());
                }
            }

            for(Board b:list){
                if(b.getParentid()==0){
                    continue;
                }
                Board parentBoard=new Board();
                parentBoard.setBoardid(b.getParentid());//借用一个空的Board放入boardid一个parentid去实现和前面的已经存放的map里的《 键 》去比较。
                                                        //map里的键为：只需要boardid(有1到4的值)的值。
                                                        //map里的值为：暂时全部为空，比较键里的boardid是否与创建的新的parentBoard对象相等（需要修改Board对象里的hashcode和equals 。)
                List<Board> sonBoardList=new ArrayList<Board>();
                if(map.containsKey(parentBoard)){
                    sonBoardList=map.get(parentBoard);
                    sonBoardList.add(b);
                }
            }

            Map<String,List<Board>> map1=new HashMap<>();
            for(Map.Entry<Board,List<Board>> entry:map.entrySet()   ){
                map1.put(entry.getKey().getBoardname(),entry.getValue());
            }

            jm.setCode(1);
            jm.setData(map1);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }


        super.writeJson(jm,response);
    }



    //一条语句查出所有
//    protected void ShowBoard2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        JsonModel jm=new JsonModel();
//
//        try {
//            String sql=" select a.boardid,boardname,parentid,total, topicid,title,modifytime,uname\n" +
//                    "from\n" +
//                    "    (\n" +
//                    "    select tbl_board.boardid,boardname,parentid , count( topicid ) as total\n" +
//                    "    from tbl_board\n" +
//                    "    left join tbl_topic\n" +
//                    "    on tbl_board.boardid=tbl_topic.boardid\n" +
//                    "    group by tbl_board.boardid,boardname,parentid\n" +
//                    "    ) a\n" +
//                    "    left join\n" +
//                    "    (\n" +
//                    "    select topicid,title,a.modifytime,uname,a.boardid\n" +
//                    "    from\n" +
//                    "    (\tselect topicid, title, modifytime, uname, boardid\n" +
//                    "    from tbl_topic\n" +
//                    "    left join tbl_user\n" +
//                    "    on tbl_topic.uid=tbl_user.uid\n" +
//                    "    ) a,\n" +
//                    "    (\n" +
//                    "    select boardid,max(modifytime) as modifytime\n" +
//                    "    from tbl_topic\n" +
//                    "    group by boardid\n" +
//                    "    ) b\n" +
//                    "    where  a.boardid=b.boardid and a.modifytime=b.modifytime\n" +
//                    "    )b\n" +
//                    "on a.boardid=b.boardid  ";
//            DBHelper db=new DBHelper();
//            List<Board> list = db.select(sql, Board.class);
//            Map<Board,List<Board>> map=new HashMap<>();
//            for(Board b:list){
//                if(b.getParentid()==0){
//                    map.put(b,new ArrayList<Board>());
//                }
//            }
//            for(Board b:list){
//                if(b.getParentid()==0){
//                    continue;
//                }
//                Board parentBoard=new Board();
//                parentBoard.setBoardid(b.getParentid());//借用一个空的Board放入boardid一个parentid去实现和前面的已经存放的map里的《 键 》去比较。
//                //map里的键为：只有boardid有1到4的值，其余值为空。
//                //map里的值为：暂时全部为空，比较键里的boardid是否与创建的新的parentBoard对象相等（需要修改Board对象里的hashcode和equals 。)
//                List<Board> sonBoardList=new ArrayList<Board>();
//                if(map.containsKey(parentBoard)){
//                    sonBoardList=map.get(parentBoard);
//                    sonBoardList.add(b);
//                }
//            }
//            jm.setCode(1);
//            jm.setData(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jm.setCode(0);
//            jm.setMsg(e.getMessage());
//        }
//        super.writeJson(jm,response);
//    }

}
