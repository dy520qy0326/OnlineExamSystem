package org.dream.www.exam.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.dto.ChoiceDto;
import org.dream.www.exam.po.Choice;
import org.dream.www.exam.repository.ChoiceRepository;
import org.dream.www.exam.service.ChoiceeService;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.util.SysConstant;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月29日 下午4:02:35 
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
@Transactional
public class ChoiceeServiceImpl implements ChoiceeService {

    @Resource
    private ChoiceRepository choiceRepository;

    @Override
    public WoPage<ChoiceDto> getPageDate(Long start, Long length, String search, String orderType,Integer questionId) {
        // 分页数据
        Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
                "desc".equals(orderType) ? Direction.DESC : Direction.ASC, "no");
        Page<Choice> data = null;
        // 判断查询字符串是否为空
        if (WoUtil.isEmpty(search)) {
            data = choiceRepository.findAllByQuestionId(questionId,p);
        } else {
            data = choiceRepository.findAllByQuestionIdAndDescriptionLike(questionId,"%" + search + "%", p);
        }
        // 将po的分页数据，转化为dto的分页数据
        return new WoPage<ChoiceDto>(ChoiceDto.getDtos(data.getContent()), data.getTotalElements());
    }

    @Override
    public WoPage<ChoiceDto> getPageDate1(Long start, Long length, String search, String orderType) {
        Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
                "desc".equals(orderType) ? Direction.DESC : Direction.ASC, "id");
        Page<Choice> data = null;
        // 判断查询字符串是否为空
        if (WoUtil.isEmpty(search)) {
            data = choiceRepository.findAll(p);
        } else {
            data = choiceRepository.findAllByDescriptionLike("%" + search + "%", p);
        }
        // 将po的分页数据，转化为dto的分页数据
        return new WoPage<ChoiceDto>(ChoiceDto.getDtos(data.getContent()), data.getTotalElements());
    }

    @Override
    public WoResultCode delete(int[] id) {


        try {
            Choice c = choiceRepository.findById(id[0]).get();



            for (int i : id) {
                this.delete (i);
            }
            List<Choice> aList = choiceRepository.findAllByQuestionId(c.getQuestion().getId());

            if (aList!=null) {
                Collections.sort(aList);
                for (int i = 0; i < aList.size(); i++) {
                    aList.get(i).setNo(((char)('A'+i))+"");
                    choiceRepository.save(aList.get(i));
                }

            }

            return WoResultCode.getSuccessCode();

        } catch (Exception e) {
            return WoResultCode.getUnknownCode();
        }


    }

    public void delete(int id) {

        choiceRepository.deleteById (id);


    }


    @Override
    public ChoiceDto findById(Integer id) {
        return   new ChoiceDto(choiceRepository.findById(id).get());
    }

    @Override
    public void update(ChoiceDto k) {
        Choice po = k.createPo();
        try {
            choiceRepository.save(po);

        } catch (Exception e) {
            throw new SysException(SysConstant.ERR_UPDATE_CHOICE);
        }

    }

    @Override
    public void create(ChoiceDto k) {
        Choice po = k.createPo();
        try {
            choiceRepository.save(po);

        } catch (Exception e) {
            throw new SysException(SysConstant.ERR_UPDATE_CHOICE);
        }

    }

    @Override
    public void upChoice(Integer id) {

        Choice c1 = choiceRepository.findById(id).get();

        List<Choice> aList = choiceRepository.findAllByQuestionId(c1.getQuestion().getId());
        Collections.sort(aList);
        if (c1.equals(aList.get(0))) {

            for (int i = 1; i < aList.size(); i++) {
                aList.get(i).setNo(((char)('A'+(i-1)))+"");
                choiceRepository.save(aList.get(i));
            }
            c1.setNo(((char)('A'+(aList.size()-1)))+"");
            choiceRepository.save(c1);

        }else {


            for (int i = 1; i <aList.size(); i++) {
                if (c1.equals(aList.get(i))) {

                    String trmp=c1.getNo();

                    Choice c2 = aList.get(i-1);

                    c1.setNo(c2.getNo());
                    c2.setNo(trmp);

                    choiceRepository.save(c2);
                    choiceRepository.save(c1);
                }
            }

        }

    }

    @Override
    public void downChoice(Integer id) {


        Choice c1 = choiceRepository.findById(id).get();

        List<Choice> aList = choiceRepository.findAllByQuestionId(c1.getQuestion().getId());
        Collections.sort(aList);
        if (c1.equals(aList.get(aList.size()-1))) {

            for (int i = 0; i < aList.size()-1; i++) {
                aList.get(i).setNo(((char)('A'+(i+1)))+"");
                choiceRepository.save(aList.get(i));
            }
            c1.setNo('A'+"");
            choiceRepository.save(c1);

        }else {


            for (int i = 0; i <aList.size()-1; i++) {
                if (c1.equals(aList.get(i))) {

                    String trmp=c1.getNo();

                    Choice c2 = aList.get(i+1);

                    c1.setNo(c2.getNo());
                    c2.setNo(trmp);

                    choiceRepository.save(c2);
                    choiceRepository.save(c1);
                }
            }

        }


    }


}
