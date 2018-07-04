package monitordemo.demo.myapp.myapplication;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/3
 * TODO:
 */
public class RxJavaDemo {
    private static final String TAG = RxJavaDemo.class.getSimpleName();

    @Test
    public void testCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("testCreate(): emit 1" + "\n");
                emitter.onNext(1);

                System.out.println("testCreate(): emit 2" + "\n");
                emitter.onNext(2);

                System.out.println("testCreate(): emit 3" + "\n");
                emitter.onNext(3);

                System.out.println("testCreate(): emit 4" + "\n");
                emitter.onNext(4);

                System.out.println("testCreate(): emit 5" + "\n");
                emitter.onNext(5);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int index;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer i) {
                System.out.println("onNext : value : " + i + "\n");
                index++;
                if (index == 3) {
                    mDisposable.isDisposed();
                    System.out.println("onNext:isDisposable:" + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete" + "\n");
            }
        });
    }

    @Test
    public void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result:" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept:" + s + "\n");
            }
        });
    }

    @Test
    public void testZip() {
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("zip:accept:" + s + "\n");
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext("A");
                    System.out.println("String emit : A \n");
                    emitter.onNext("B");
                    System.out.println("String emit : B \n");
                    emitter.onNext("C");
                    System.out.println("String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(1);
                    System.out.println("String emit : 1 \n");
                    emitter.onNext(2);
                    System.out.println("String emit : 2 \n");
                    emitter.onNext(3);
                    System.out.println("String emit : 3 \n");

                    emitter.onNext(4);
                    System.out.println("String emit : 4 \n");
                    emitter.onNext(5);
                    System.out.println("String emit : 5 \n");
                }
            }
        });
    }

    @Test
    public void testConcat() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(7, 8, 9))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) throws Exception {
                        System.out.println("concat: " + i + "\n");
                    }
                });
    }

    @Test
    public void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                System.out.println("emitter: 4");
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value:" + integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("flatMap: accept: " + s + "\n");
                    }
                });

    }

    //AndroidSchedulers.mainThread()需要RxAndroid库，而且不能在java单元测试文件夹使用
    @Test
    public void testConcatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value:" + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("concatMap: accept: " + s + "\n");
                    }
                });
    }

    @Test
    public void testFlowable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Test
    public void testConsumer() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
