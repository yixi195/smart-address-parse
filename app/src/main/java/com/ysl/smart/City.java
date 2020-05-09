package com.ysl.smart;

import java.util.List;

/**
 * Created by YangShlai on 2020/3/24.
 */
public class City {
    /**
     * areaId : 130000
     * areaName : 河北省
     * cities : [{"areaId":"130100","areaName":"石家庄市","counties":[{"areaId":"130102","areaName":"长安区"},{"areaId":"130103","areaName":"桥东区"},{"areaId":"130104","areaName":"桥西区"},{"areaId":"130105","areaName":"新华区"},{"areaId":"130107","areaName":"井陉矿区"},{"areaId":"130108","areaName":"裕华区"},{"areaId":"130121","areaName":"井陉县"},{"areaId":"130123","areaName":"正定县"},{"areaId":"130124","areaName":"栾城县"},{"areaId":"130125","areaName":"行唐县"},{"areaId":"130126","areaName":"灵寿县"},{"areaId":"130127","areaName":"高邑县"},{"areaId":"130128","areaName":"深泽县"},{"areaId":"130129","areaName":"赞皇县"},{"areaId":"130130","areaName":"无极县"},{"areaId":"130131","areaName":"平山县"},{"areaId":"130132","areaName":"元氏县"},{"areaId":"130133","areaName":"赵县"},{"areaId":"130181","areaName":"辛集市"},{"areaId":"130182","areaName":"藁城市"},{"areaId":"130183","areaName":"晋州市"},{"areaId":"130184","areaName":"新乐市"},{"areaId":"130185","areaName":"鹿泉市"}]},{"areaId":"130200","areaName":"唐山市","counties":[{"areaId":"130202","areaName":"路南区"},{"areaId":"130203","areaName":"路北区"},{"areaId":"130204","areaName":"古冶区"},{"areaId":"130205","areaName":"开平区"},{"areaId":"130207","areaName":"丰南区"},{"areaId":"130208","areaName":"丰润区"},{"areaId":"130209","areaName":"曹妃甸区"},{"areaId":"130223","areaName":"滦县"},{"areaId":"130224","areaName":"滦南县"},{"areaId":"130225","areaName":"乐亭县"},{"areaId":"130227","areaName":"迁西县"},{"areaId":"130229","areaName":"玉田县"},{"areaId":"130281","areaName":"遵化市"},{"areaId":"130283","areaName":"迁安市"}]},{"areaId":"130300","areaName":"秦皇岛市","counties":[{"areaId":"130302","areaName":"海港区"},{"areaId":"130303","areaName":"山海关区"},{"areaId":"130304","areaName":"北戴河区"},{"areaId":"130321","areaName":"青龙满族自治县"},{"areaId":"130322","areaName":"昌黎县"},{"areaId":"130323","areaName":"抚宁县"},{"areaId":"130324","areaName":"卢龙县"}]},{"areaId":"130400","areaName":"邯郸市","counties":[{"areaId":"130402","areaName":"邯山区"},{"areaId":"130403","areaName":"丛台区"},{"areaId":"130404","areaName":"复兴区"},{"areaId":"130406","areaName":"峰峰矿区"},{"areaId":"130421","areaName":"邯郸县"},{"areaId":"130423","areaName":"临漳县"},{"areaId":"130424","areaName":"成安县"},{"areaId":"130425","areaName":"大名县"},{"areaId":"130426","areaName":"涉县"},{"areaId":"130427","areaName":"磁县"},{"areaId":"130428","areaName":"肥乡县"},{"areaId":"130429","areaName":"永年县"},{"areaId":"130430","areaName":"邱县"},{"areaId":"130431","areaName":"鸡泽县"},{"areaId":"130432","areaName":"广平县"},{"areaId":"130433","areaName":"馆陶县"},{"areaId":"130434","areaName":"魏县"},{"areaId":"130435","areaName":"曲周县"},{"areaId":"130481","areaName":"武安市"}]},{"areaId":"130500","areaName":"邢台市","counties":[{"areaId":"130502","areaName":"桥东区"},{"areaId":"130503","areaName":"桥西区"},{"areaId":"130521","areaName":"邢台县"},{"areaId":"130522","areaName":"临城县"},{"areaId":"130523","areaName":"内丘县"},{"areaId":"130524","areaName":"柏乡县"},{"areaId":"130525","areaName":"隆尧县"},{"areaId":"130526","areaName":"任县"},{"areaId":"130527","areaName":"南和县"},{"areaId":"130528","areaName":"宁晋县"},{"areaId":"130529","areaName":"巨鹿县"},{"areaId":"130530","areaName":"新河县"},{"areaId":"130531","areaName":"广宗县"},{"areaId":"130532","areaName":"平乡县"},{"areaId":"130533","areaName":"威县"},{"areaId":"130534","areaName":"清河县"},{"areaId":"130535","areaName":"临西县"},{"areaId":"130581","areaName":"南宫市"},{"areaId":"130582","areaName":"沙河市"}]},{"areaId":"130600","areaName":"保定市","counties":[{"areaId":"130602","areaName":"新市区"},{"areaId":"130603","areaName":"北市区"},{"areaId":"130604","areaName":"南市区"},{"areaId":"130621","areaName":"满城县"},{"areaId":"130622","areaName":"清苑县"},{"areaId":"130623","areaName":"涞水县"},{"areaId":"130624","areaName":"阜平县"},{"areaId":"130625","areaName":"徐水县"},{"areaId":"130626","areaName":"定兴县"},{"areaId":"130627","areaName":"唐县"},{"areaId":"130628","areaName":"高阳县"},{"areaId":"130629","areaName":"容城县"},{"areaId":"130630","areaName":"涞源县"},{"areaId":"130631","areaName":"望都县"},{"areaId":"130632","areaName":"安新县"},{"areaId":"130633","areaName":"易县"},{"areaId":"130634","areaName":"曲阳县"},{"areaId":"130635","areaName":"蠡县"},{"areaId":"130636","areaName":"顺平县"},{"areaId":"130637","areaName":"博野县"},{"areaId":"130638","areaName":"雄县"},{"areaId":"130681","areaName":"涿州市"},{"areaId":"130682","areaName":"定州市"},{"areaId":"130683","areaName":"安国市"},{"areaId":"130684","areaName":"高碑店市"}]},{"areaId":"130700","areaName":"张家口市","counties":[{"areaId":"130702","areaName":"桥东区"},{"areaId":"130703","areaName":"桥西区"},{"areaId":"130705","areaName":"宣化区"},{"areaId":"130706","areaName":"下花园区"},{"areaId":"130721","areaName":"宣化县"},{"areaId":"130722","areaName":"张北县"},{"areaId":"130723","areaName":"康保县"},{"areaId":"130724","areaName":"沽源县"},{"areaId":"130725","areaName":"尚义县"},{"areaId":"130726","areaName":"蔚县"},{"areaId":"130727","areaName":"阳原县"},{"areaId":"130728","areaName":"怀安县"},{"areaId":"130729","areaName":"万全县"},{"areaId":"130730","areaName":"怀来县"},{"areaId":"130731","areaName":"涿鹿县"},{"areaId":"130732","areaName":"赤城县"},{"areaId":"130733","areaName":"崇礼县"}]},{"areaId":"130800","areaName":"承德市","counties":[{"areaId":"130802","areaName":"双桥区"},{"areaId":"130803","areaName":"双滦区"},{"areaId":"130804","areaName":"鹰手营子矿区"},{"areaId":"130821","areaName":"承德县"},{"areaId":"130822","areaName":"兴隆县"},{"areaId":"130823","areaName":"平泉县"},{"areaId":"130824","areaName":"滦平县"},{"areaId":"130825","areaName":"隆化县"},{"areaId":"130826","areaName":"丰宁满族自治县"},{"areaId":"130827","areaName":"宽城满族自治县"},{"areaId":"130828","areaName":"围场满族蒙古族自治县"}]},{"areaId":"130900","areaName":"沧州市","counties":[{"areaId":"130902","areaName":"新华区"},{"areaId":"130903","areaName":"运河区"},{"areaId":"130921","areaName":"沧县"},{"areaId":"130922","areaName":"青县"},{"areaId":"130923","areaName":"东光县"},{"areaId":"130924","areaName":"海兴县"},{"areaId":"130925","areaName":"盐山县"},{"areaId":"130926","areaName":"肃宁县"},{"areaId":"130927","areaName":"南皮县"},{"areaId":"130928","areaName":"吴桥县"},{"areaId":"130929","areaName":"献县"},{"areaId":"130930","areaName":"孟村回族自治县"},{"areaId":"130981","areaName":"泊头市"},{"areaId":"130982","areaName":"任丘市"},{"areaId":"130983","areaName":"黄骅市"},{"areaId":"130984","areaName":"河间市"}]},{"areaId":"131000","areaName":"廊坊市","counties":[{"areaId":"131002","areaName":"安次区"},{"areaId":"131003","areaName":"广阳区"},{"areaId":"131022","areaName":"固安县"},{"areaId":"131023","areaName":"永清县"},{"areaId":"131024","areaName":"香河县"},{"areaId":"131025","areaName":"大城县"},{"areaId":"131026","areaName":"文安县"},{"areaId":"131028","areaName":"大厂回族自治县"},{"areaId":"131081","areaName":"霸州市"},{"areaId":"131082","areaName":"三河市"}]},{"areaId":"131100","areaName":"衡水市","counties":[{"areaId":"131102","areaName":"桃城区"},{"areaId":"131121","areaName":"枣强县"},{"areaId":"131122","areaName":"武邑县"},{"areaId":"131123","areaName":"武强县"},{"areaId":"131124","areaName":"饶阳县"},{"areaId":"131125","areaName":"安平县"},{"areaId":"131126","areaName":"故城县"},{"areaId":"131127","areaName":"景县"},{"areaId":"131128","areaName":"阜城县"},{"areaId":"131181","areaName":"冀州市"},{"areaId":"131182","areaName":"深州市"}]}]
     */
    private String areaId;
    private String areaName; //省
    private String matchValue; //正则匹配值
    public int index;

    public String getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(String matchValue) {
        this.matchValue = matchValue;
    }

    private List<CitiesBean> cities;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean {
        /**
         * areaId : 130100
         * areaName : 石家庄市
         * counties : [{"areaId":"130102","areaName":"长安区"},{"areaId":"130103","areaName":"桥东区"},{"areaId":"130104","areaName":"桥西区"},{"areaId":"130105","areaName":"新华区"},{"areaId":"130107","areaName":"井陉矿区"},{"areaId":"130108","areaName":"裕华区"},{"areaId":"130121","areaName":"井陉县"},{"areaId":"130123","areaName":"正定县"},{"areaId":"130124","areaName":"栾城县"},{"areaId":"130125","areaName":"行唐县"},{"areaId":"130126","areaName":"灵寿县"},{"areaId":"130127","areaName":"高邑县"},{"areaId":"130128","areaName":"深泽县"},{"areaId":"130129","areaName":"赞皇县"},{"areaId":"130130","areaName":"无极县"},{"areaId":"130131","areaName":"平山县"},{"areaId":"130132","areaName":"元氏县"},{"areaId":"130133","areaName":"赵县"},{"areaId":"130181","areaName":"辛集市"},{"areaId":"130182","areaName":"藁城市"},{"areaId":"130183","areaName":"晋州市"},{"areaId":"130184","areaName":"新乐市"},{"areaId":"130185","areaName":"鹿泉市"}]
         */

        private String areaId;
        private String areaName; //市
        private String matchValue; //正则匹配值
        public int index;
        private List<CountiesBean> counties;

        public String getMatchValue() {
            return matchValue;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<CountiesBean> getCounties() {
            return counties;
        }

        public void setCounties(List<CountiesBean> counties) {
            this.counties = counties;
        }

        public static class CountiesBean {
            /**
             * areaId : 130102
             * areaName : 长安区
             */

            private String areaId;
            private String areaName; //区
            private String matchValue; //正则匹配值
            public int index;

            public String getMatchValue() {
                return matchValue;
            }

            public void setMatchValue(String matchValue) {
                this.matchValue = matchValue;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }
        }
    }
}
