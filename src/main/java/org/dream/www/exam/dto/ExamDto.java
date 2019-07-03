package org.dream.www.exam.dto;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.po.Exam;
import org.dream.www.exam.po.Paper;
import org.dream.www.sys.po.User;

    /**
     * 考试
     */
    public class ExamDto {

        /**
         * 主键，自增长
         */

        private Integer id;

        /**
         * 名称
         */
        private String name;

        /**
         * 开始时间
         */
        private Date startTime;

        /**
         * 结束时间：如果该属性有值，表示考试结束
         */
        private Date endTime;

        /**
         * 描述
         */
        private String description;

        /**
         * 试卷：多对多
         */
        private List<Paper> papers;
        
        private String papersId;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 考试与创建人用户：多对一
         */

        private String createUserId;
        private String createUserName;
        
        
        /**
		 * @return the createUserName
		 */
		public String getCreateUserName() {
			return createUserName;
		}



		/**
		 * @param createUserName the createUserName to set
		 */
		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}



		/**
		 * @return the createUserId
		 */
		public String getCreateUserId() {
			return createUserId;
		}



		/**
		 * @param createUserId the createUserId to set
		 */
		public void setCreateUserId(String createUserId) {
			this.createUserId = createUserId;
		}

		private List<User> Students;
        
        



        /**
		 * @return the students
		 */
		public List<User> getStudents() {
			return Students;
		}



		/**
		 * @param students the students to set
		 */
		public void setStudents(List<User> students) {
			Students = students;
		}



		public Integer getId() {
            return id;
        }



        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8")
        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8")
        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8")
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public List<Paper> getPapers() {
            return papers;
        }

        public void setPapers(List<Paper> papers) {
            this.papers = papers;
        }

        public ExamDto() {
            super();
        }


        public ExamDto(Exam exam){
            this.id=exam.getId();
            this.name=exam.getName();
            this.createTime=exam.getCreateTime();
            this.description=exam.getDescription();
            this.startTime=exam.getStartTime();
            this.endTime=exam.getEndTime();
            this.papers=exam.getPapers();
            this.createUserId= exam.getCreateUser().getId().toString();


        }

        //将po集合转化为Dto
        public static List<ExamDto> getDtos(List<Exam> pos){
            List<ExamDto> dtos = new ArrayList<ExamDto>();
            for (Exam po : pos){
                dtos.add(new ExamDto(po));
            }
        return dtos;
        }



		public Exam createPo() {
			
			Exam po = new Exam();
			
			po.setId(id);
			po.setName(name);
			po.setDescription(description);
			
			if (!WoUtil.isEmpty(createUserId)) {
				
				User user = new User();
				user.setId(Integer.parseInt(createUserId));
				po.setCreateUser(user);
			}
			if (!WoUtil.isEmpty(papersId)) {
				
				String[] pList = papersId.split(",");
				ArrayList<Paper> paList = new ArrayList<Paper>();
				for (String pid : pList) {
					
					Paper addpaper = new Paper();
					
					addpaper.setId(Integer.parseInt(pid));
					paList.add(addpaper);
				}
				po.setPapers(paList);
			}
			
			po.setCreateTime(createTime);
			po.setEndTime(endTime);
			po.setStartTime(startTime);
			return po;
			
			
		}

    }


