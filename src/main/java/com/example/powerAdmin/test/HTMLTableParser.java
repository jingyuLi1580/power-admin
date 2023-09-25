package com.example.powerAdmin.test;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

public class HTMLTableParser {

    public static void main(String[] args) {
        String query = "城投";
        String html = "<table><tbody><tr> <td>时间</td> <td>地区</td> <td>场合</td> <td>表态内容</td> <td>资料来源</td></tr><tr> <td>2021-12-14</td> <td>天津市</td> <td>天津市国资委党委委员、副主任张蕾带队到天津<em>城投</em>集团市政投资公司调研指导工作</td> <td>一是要深化沟通，配合打造高质量产业基金群，把投资者留在天津，为城投集团下一步业务发展提供资金保障；二是要在整体化债工作基本稳定的情况下，抓住政策调整机遇，加快速度推出新的融资产品，为进一步转型发展赢得时间；三是要加强财务管理，加大财务监督检查工作力度，确保资金正常运转；四是要加强干部队伍建设，发挥党总支正向引领作用，更好的调动员工积极性，为员工搭建一个能够充分体现价值、快速成长发展的平台；五是要持续抓牢安全生产和疫情防控工作，以“十四五”战略规划为核心，切实做到高质量发展，努力成为<em>城投</em>集团中的佼佼者。</td> <td>市国资委深入天津城投集团市政投资公司调研指导工作</td></tr><tr> <td>2021-12-17</td> <td>证监会</td> <td>清理整顿各类交易场所部际联席会议部署开展金交所现场检查工作</td> <td>明确金交所应当恪守合规性、区域性、非涉众的原则；不得为发行销售非标债务融资产品提供服务和便利，严控新增、持续压降各类非标融资主体的融资业务；禁止金交所为房地产企业（项目）、<em>城投</em>公司等国家限制或有特定规范要求的企业融资；严格落实不得直接或间接向个人销售产品、不得跨省异地展业的底线要求；不得新设金交所，辖内已有多家金交所的，根据“一省至多一家”原则推进整合工作。</td> <td>http://www.csrc.gov.cn/csrc/c100028/c1656428/content.shtml</td></tr><tr> <td>2021-12-22</td> <td>广州市</td> <td>广州市国资委党委书记、主任陈德俊率队赴广州<em>城投</em>集团开展工作调研</td> <td>一是要坚持党的领导，加强党的建设，扎实推进国企党建与企业生产经营深度融合；二是要强化风险防控防范意识，切实抓好安全生产、疫情防控、风险防控，认真分析研判存量项目历史风险，分类施策、精准化解；三是要强化资本运作能力，进一步提高资产运营效率，科学研判投资收益，实现国有资产保值增值；四是要要高度重视、科学谋划投资项目调研和决策，严控项目投资风险，进一步加强对重大投资项目的事前指导和监管，确保国有资产保值增值，更好服务全市经济社会发展大局。</td> <td>http://gzw.gz.gov.cn/xw/gzdt/content/post_7979611.html</td></tr></tbody></table>";
        Document doc = Jsoup.parse(html);
        Element table = doc.select("table").get(0);
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements columns = row.select("td");
            for (Element column : columns) {
                String str = column.text();
                //判断是否包含搜索词，并且长度超过150
                int index = str.indexOf(query); // 找到关键词"城投"的索引位置
                if (index != -1 && str.length() > 150) {
                    String substring = str.substring(0, index);
                    int punctuationIndex = getLastPunctuationIndex(substring);
                    System.out.println("命中词前一个标点符号下标位置 = " + punctuationIndex);
                    int lastNum = index+100;
                    if (index+100 >= str.length() -1) {
                        lastNum = str.length() -1;
                    }
                    String substring1 = str.substring(0, lastNum);
                    int punctuationIndex1 = getLastPunctuationIndex(substring1);
                    System.out.println("命中词后100字符内最后一个标点符号下标位置 = " + punctuationIndex1);
                    if (punctuationIndex1 != -1) {
                        String result = str.substring(punctuationIndex + 1, punctuationIndex1);
                        column.text(result);
                        System.out.println(result);
                    } else {
                        System.out.println("未找到符合条件的内容。");
                    }
                }
            }
        }
        System.out.println("table = " + table.outerHtml());
    }

    private static int getLastPunctuationIndex(String str) {
        Set<Character> punctuations = new HashSet<>();
        punctuations.add('，');
        punctuations.add('。');
        punctuations.add('；');
        punctuations.add('！');
        punctuations.add('？');

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (punctuations.contains(c)) {
                return i;
            }
        }
        return -1;
    }

}
