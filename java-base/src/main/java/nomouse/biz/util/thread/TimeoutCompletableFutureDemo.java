package nomouse.biz.util.thread;


import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import nomouse.biz.util.exception.BizException;

/**
 * @author wuchunhao on 2023/2/22
 */
@Slf4j
public class TimeoutCompletableFutureDemo {

    private static List<String> parallel(List<Long> idList) {

        CompletableFuture[] array = idList.stream()
            .map(id -> {
                CompletableFuture<String> future =
                    CompletableFutureUtils
                        .orTimeout(CompletableFuture
                                .supplyAsync(() -> singleHandle(id), CustomThreadPool.get()),
                            3, TimeUnit.SECONDS)
                        .whenComplete((m, e) -> {
                            log.info(
                                "|singleHandle|Single|Y||id={}|", id);
                        })
                        .exceptionally(e -> {
                            log.error("|singleHandle|Single|Y||id={}|", id, e);
                            return null;
                        });
                return future;
            }).toArray(CompletableFuture[]::new);

        int size = idList.size();
        List<String> result = new ArrayList<>(size);
        try {
            // 全部线程返回后处理
            CompletableFuture.allOf(array)
                .get(5, TimeUnit.SECONDS);

            for (int i = 0; i < size; i++) {
                CompletableFuture<String> future = array[i];
                if (future.isDone()) {
                    String partitionResult = future.getNow(null);
                    result.add(partitionResult);
                }
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("|getEmployeeDisplayMap|Batch|N|list={}", JSON.toJSONString(idList), e);
            throw new BizException(e.toString(), "并行处理异常");
        } catch (Exception e) {
            log.error("|getEmployeeDisplayMap|Batch|N|list={}", JSON.toJSONString(idList), e);
            throw e;
        }

        return result;
    }


    private static String singleHandle(Long i) {
        return i + "_test";
    }

    public static void main(String[] args) {
        List<Long> idList = new ArrayList<>();

        for (long i = 0; i < 30; i++) {
            idList.add(i);
        }

        List<String> resultList = parallel(idList);

        System.out.println(resultList);
    }

}
