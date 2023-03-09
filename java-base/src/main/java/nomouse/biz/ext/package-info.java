/**
 * <p>
 * 整体结构：
 *               StrategyExtPoint
 *              ｜              ｜
 *          ExtPointA        ExtPointB
 *          ｜      ｜       ｜       ｜
 *        Ext1     Ext2    Ext3     Ext4
 * <p/>
 * <p>
 * 业务扩展点(ExtPointA、ExtPointB)：每个业务扩展点满足 1.接口；2.继承StrategyExtPoint接口；3.必须是直接继承，不支持多层继承。
 * 业务扩展实例(Ext1、Ext2、Ext3)：每个业务扩展实例满足 1.实现业务扩展点接口；2.实现StrategyExt注解且设置indentityList和bizIdList；3.同一个业务扩展点下bizKeys不能有重复，否则会初始化失败。
 * </p>
 * @author wuchunhao on 2022/9/28
 */
package nomouse.biz.ext;