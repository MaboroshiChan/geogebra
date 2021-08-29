package org.geogebra.common.kernel.interval;

import static org.geogebra.common.kernel.interval.IntervalTest.interval;
import static org.geogebra.common.kernel.interval.IntervalTest.invertedInterval;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SqrtSamplerTest extends SamplerTest {

	@Test
	public void sqrtOfXInverse() {
		IntervalTupleList tuples = functionValuesWithSampleCount("sqrt(1/x)",
				-1, 12, -12, 12, 100);
		assertEquals(IntervalConstants.empty(), tuples.valueAt(0));
		assertEquals(invertedInterval(0.0, 4.999999999999999), tuples.valueAt(1));
		assertEquals(interval(2.425356250363329, 4.999999999999999), tuples.valueAt(2));
		assertEquals(interval(1.8257418583505534, 2.42535625036333), tuples.valueAt(3));
		assertEquals(interval(1.524985703326046, 1.825741858350554), tuples.valueAt(4));
		assertEquals(interval(1.3363062095621216, 1.524985703326047), tuples.valueAt(5));
		assertEquals(interval(1.2038585308576917, 1.336306209562122), tuples.valueAt(6));
		assertEquals(interval(1.1043152607484652, 1.2038585308576921), tuples.valueAt(7));
		assertEquals(interval(1.0259783520851538, 1.1043152607484656), tuples.valueAt(8));
		assertEquals(interval(0.962250448649376, 1.0259783520851544), tuples.valueAt(9));
		assertEquals(interval(0.909090909090909, 0.9622504486493764), tuples.valueAt(10));
		assertEquals(interval(0.86386842558136, 0.9090909090909093), tuples.valueAt(11));
		assertEquals(interval(0.8247860988423223, 0.8638684255813603), tuples.valueAt(12));
		assertEquals(interval(0.7905694150420948, 0.8247860988423228), tuples.valueAt(13));
		assertEquals(interval(0.7602859212697054, 0.7905694150420951), tuples.valueAt(14));
		assertEquals(interval(0.7332355751067664, 0.7602859212697058), tuples.valueAt(15));
		assertEquals(interval(0.7088812050083358, 0.7332355751067667), tuples.valueAt(16));
		assertEquals(interval(0.6868028197434451, 0.7088812050083362), tuples.valueAt(17));
		assertEquals(interval(0.6666666666666666, 0.6868028197434454), tuples.valueAt(18));
		assertEquals(interval(0.6482037235521644, 0.666666666666667), tuples.valueAt(19));
		assertEquals(interval(0.6311944030978032, 0.6482037235521647), tuples.valueAt(20));
		assertEquals(interval(0.6154574548966636, 0.6311944030978035), tuples.valueAt(21));
		assertEquals(interval(0.6008417681261097, 0.6154574548966639), tuples.valueAt(22));
		assertEquals(interval(0.5872202195147035, 0.60084176812611), tuples.valueAt(23));
		assertEquals(interval(0.574484989621426, 0.5872202195147038), tuples.valueAt(24));
		assertEquals(interval(0.562543950463012, 0.5744849896214264), tuples.valueAt(25));
		assertEquals(interval(0.5513178464199713, 0.5625439504630123), tuples.valueAt(26));
		assertEquals(interval(0.5407380704358751, 0.5513178464199716), tuples.valueAt(27));
		assertEquals(interval(0.5307448924342753, 0.5407380704358754), tuples.valueAt(28));
		assertEquals(interval(0.5212860351426869, 0.5307448924342756), tuples.valueAt(29));
		assertEquals(interval(0.5123155195785599, 0.5212860351426872), tuples.valueAt(30));
		assertEquals(interval(0.5037927218598783, 0.5123155195785603), tuples.valueAt(31));
		assertEquals(interval(0.49568159709660975, 0.5037927218598787), tuples.valueAt(32));
		assertEquals(interval(0.48795003647426666, 0.4956815970966099), tuples.valueAt(33));
		assertEquals(interval(0.4805693313322128, 0.4879500364742668), tuples.valueAt(34));
		assertEquals(interval(0.47351372381037843, 0.48056933133221297), tuples.valueAt(35));
		assertEquals(interval(0.46676002800933664, 0.47351372381037865), tuples.valueAt(36));
		assertEquals(interval(0.46028730894916176, 0.4667600280093368), tuples.valueAt(37));
		assertEquals(interval(0.45407660918649984, 0.4602873089491619), tuples.valueAt(38));
		assertEquals(interval(0.4481107149482209, 0.45407660918650006), tuples.valueAt(39));
		assertEquals(interval(0.4423739552038089, 0.4481107149482211), tuples.valueAt(40));
		assertEquals(interval(0.436852028330519, 0.44237395520380907), tuples.valueAt(41));
		assertEquals(interval(0.4315318520021031, 0.4368520283305192), tuples.valueAt(42));
		assertEquals(interval(0.42640143271122094, 0.4315318520021033), tuples.valueAt(43));
		assertEquals(interval(0.42144975196108964, 0.4264014327112211), tuples.valueAt(44));
		assertEquals(interval(0.4166666666666667, 0.4214497519610898), tuples.valueAt(45));
		assertEquals(interval(0.4120428217151646, 0.4166666666666669), tuples.valueAt(46));
		assertEquals(interval(0.40756957296961127, 0.4120428217151648), tuples.valueAt(47));
		assertEquals(interval(0.40323891927275596, 0.40756957296961144), tuples.valueAt(48));
		assertEquals(interval(0.3990434422338111, 0.4032389192727561), tuples.valueAt(49));
		assertEquals(interval(0.3949762527666822, 0.3990434422338113), tuples.valueAt(50));
		assertEquals(interval(0.39103094350288753, 0.39497625276668236), tuples.valueAt(51));
		assertEquals(interval(0.3872015463311832, 0.39103094350288775), tuples.valueAt(52));
		assertEquals(interval(0.38348249442368526, 0.38720154633118337), tuples.valueAt(53));
		assertEquals(interval(0.3798685881987932, 0.3834824944236854), tuples.valueAt(54));
		assertEquals(interval(0.37635496474749075, 0.3798685881987934), tuples.valueAt(55));
		assertEquals(interval(0.3729370703141008, 0.3763549647474909), tuples.valueAt(56));
		assertEquals(interval(0.36961063547728645, 0.372937070314101), tuples.valueAt(57));
		assertEquals(interval(0.36637165272365585, 0.3696106354772866), tuples.valueAt(58));
		assertEquals(interval(0.363216356146074, 0.36637165272365607), tuples.valueAt(59));
		assertEquals(interval(0.3601412030328099, 0.3632163561460742), tuples.valueAt(60));
		assertEquals(interval(0.35714285714285715, 0.3601412030328101), tuples.valueAt(61));
		assertEquals(interval(0.3542181734879073, 0.3571428571428574), tuples.valueAt(62));
		assertEquals(interval(0.3513641844631533, 0.35421817348790746), tuples.valueAt(63));
		assertEquals(interval(0.3485780871878749, 0.3513641844631535), tuples.valueAt(64));
		assertEquals(interval(0.34585723193303736, 0.3485780871878751), tuples.valueAt(65));
		assertEquals(interval(0.3431991115272901, 0.34585723193303747), tuples.valueAt(66));
		assertEquals(interval(0.34060135164508576, 0.34319911152729027), tuples.valueAt(67));
		assertEquals(interval(0.3380617018914066, 0.3406013516450859), tuples.valueAt(68));
		assertEquals(interval(0.3355780276070121, 0.3380617018914067), tuples.valueAt(69));
		assertEquals(interval(0.3331483023263847, 0.3355780276070123), tuples.valueAt(70));
		assertEquals(interval(0.3307706008278112, 0.33314830232638487), tuples.valueAt(71));
		assertEquals(interval(0.32844309272143096, 0.33077060082781135), tuples.valueAt(72));
		assertEquals(interval(0.326164036526721, 0.32844309272143113), tuples.valueAt(73));
		assertEquals(interval(0.3239317741958683, 0.32616403652672116), tuples.valueAt(74));
		assertEquals(interval(0.3217447260438933, 0.3239317741958685), tuples.valueAt(75));
		assertEquals(interval(0.31960138605029637, 0.3217447260438935), tuples.valueAt(76));
		assertEquals(interval(0.3175003175004761, 0.31960138605029653), tuples.valueAt(77));
		assertEquals(interval(0.3154401489382557, 0.31750031750047625), tuples.valueAt(78));
		assertEquals(interval(0.3134195704036112, 0.31544014893825584), tuples.valueAt(79));
		assertEquals(interval(0.3114373299321496, 0.3134195704036113), tuples.valueAt(80));
		assertEquals(interval(0.3094922302950862, 0.31143732993214973), tuples.valueAt(81));
		assertEquals(interval(0.3075831259604323, 0.3094922302950864), tuples.valueAt(82));
		assertEquals(interval(0.3057089202578713, 0.30758312596043247), tuples.valueAt(83));
		assertEquals(interval(0.30386856273138174, 0.30570892025787144), tuples.valueAt(84));
		assertEquals(interval(0.30206104666508826, 0.3038685627313819), tuples.valueAt(85));
		assertEquals(interval(0.30028540676910187, 0.3020610466650884), tuples.valueAt(86));
		assertEquals(interval(0.29854071701326584, 0.30028540676910204), tuples.valueAt(87));
		assertEquals(interval(0.29682608859776216, 0.29854071701326595), tuples.valueAt(88));
		assertEquals(interval(0.2951406680504774, 0.2968260885977623), tuples.valueAt(89));
		assertEquals(interval(0.29348363544187434, 0.29514066805047756), tuples.valueAt(90));
		assertEquals(interval(0.29185420270888923, 0.2934836354418745), tuples.valueAt(91));
		assertEquals(interval(0.2902516120800707, 0.2918542027088894), tuples.valueAt(92));
		assertEquals(interval(0.2886751345948126, 0.2902516120800709), tuples.valueAt(93));
	}

	@Test
	public void minusSqrtOfXInverse() {
		IntervalTupleList tuples = functionValuesWithSampleCount("-sqrt(1/x)",
				-1, 12, -12, 12, 100);
		assertEquals(IntervalConstants.empty(), tuples.valueAt(0));
		assertEquals(invertedInterval(-5.0, 4.9E-324), tuples.valueAt(1));
		assertEquals(interval(-5.0, -2.4253562503633286), tuples.valueAt(2));		assertEquals(interval(-1.8257418583505542, -1.5249857033260459), tuples.valueAt(4));
		assertEquals(interval(-1.5249857033260472, -1.3363062095621214), tuples.valueAt(5));
		assertEquals(interval(-1.3363062095621223, -1.2038585308576915), tuples.valueAt(6));
		assertEquals(interval(-1.2038585308576923, -1.104315260748465), tuples.valueAt(7));
		assertEquals(interval(-1.1043152607484659, -1.0259783520851535), tuples.valueAt(8));
		assertEquals(interval(-1.0259783520851546, -0.9622504486493759), tuples.valueAt(9));
		assertEquals(interval(-0.9622504486493765, -0.9090909090909088), tuples.valueAt(10));
		assertEquals(interval(-0.9090909090909094, -0.8638684255813599), tuples.valueAt(11));
		assertEquals(interval(-0.8638684255813605, -0.8247860988423222), tuples.valueAt(12));
		assertEquals(interval(-0.8247860988423229, -0.7905694150420947), tuples.valueAt(13));
		assertEquals(interval(-0.7905694150420952, -0.7602859212697053), tuples.valueAt(14));
		assertEquals(interval(-0.760285921269706, -0.7332355751067663), tuples.valueAt(15));
		assertEquals(interval(-0.7332355751067668, -0.7088812050083357), tuples.valueAt(16));
		assertEquals(interval(-0.7088812050083363, -0.686802819743445), tuples.valueAt(17));
		assertEquals(interval(-0.6868028197434455, -0.6666666666666665), tuples.valueAt(18));
		assertEquals(interval(-0.6666666666666671, -0.6482037235521643), tuples.valueAt(19));
		assertEquals(interval(-0.6482037235521648, -0.6311944030978031), tuples.valueAt(20));
		assertEquals(interval(-0.6311944030978036, -0.6154574548966635), tuples.valueAt(21));
		assertEquals(interval(-0.615457454896664, -0.6008417681261096), tuples.valueAt(22));
		assertEquals(interval(-0.6008417681261101, -0.5872202195147034), tuples.valueAt(23));
		assertEquals(interval(-0.5872202195147039, -0.5744849896214259), tuples.valueAt(24));
		assertEquals(interval(-0.5744849896214265, -0.5625439504630119), tuples.valueAt(25));
		assertEquals(interval(-0.5625439504630124, -0.5513178464199712), tuples.valueAt(26));
		assertEquals(interval(-0.5513178464199717, -0.540738070435875), tuples.valueAt(27));
		assertEquals(interval(-0.5407380704358755, -0.5307448924342751), tuples.valueAt(28));
		assertEquals(interval(-0.5307448924342757, -0.5212860351426868), tuples.valueAt(29));
		assertEquals(interval(-0.5212860351426873, -0.5123155195785598), tuples.valueAt(30));
		assertEquals(interval(-0.5123155195785604, -0.5037927218598782), tuples.valueAt(31));
		assertEquals(interval(-0.5037927218598788, -0.4956815970966097), tuples.valueAt(32));
		assertEquals(interval(-0.49568159709660997, -0.4879500364742666), tuples.valueAt(33));
		assertEquals(interval(-0.4879500364742669, -0.48056933133221275), tuples.valueAt(34));
		assertEquals(interval(-0.480569331332213, -0.4735137238103784), tuples.valueAt(35));
		assertEquals(interval(-0.4735137238103787, -0.4667600280093366), tuples.valueAt(36));
		assertEquals(interval(-0.46676002800933686, -0.4602873089491617), tuples.valueAt(37));
		assertEquals(interval(-0.460287308949162, -0.4540766091864998), tuples.valueAt(38));
		assertEquals(interval(-0.4540766091865001, -0.44811071494822086), tuples.valueAt(39));
		assertEquals(interval(-0.44811071494822113, -0.44237395520380884), tuples.valueAt(40));
		assertEquals(interval(-0.4423739552038091, -0.43685202833051895), tuples.valueAt(41));
		assertEquals(interval(-0.4368520283305193, -0.43153185200210303), tuples.valueAt(42));
		assertEquals(interval(-0.43153185200210337, -0.4264014327112209), tuples.valueAt(43));
		assertEquals(interval(-0.42640143271122116, -0.4214497519610896), tuples.valueAt(44));
		assertEquals(interval(-0.42144975196108986, -0.41666666666666663), tuples.valueAt(45));
		assertEquals(interval(-0.41666666666666696, -0.41204282171516454), tuples.valueAt(46));
		assertEquals(interval(-0.4120428217151649, -0.4075695729696112), tuples.valueAt(47));
		assertEquals(interval(-0.4075695729696115, -0.4032389192727559), tuples.valueAt(48));
		assertEquals(interval(-0.4032389192727562, -0.39904344223381105), tuples.valueAt(49));
		assertEquals(interval(-0.39904344223381133, -0.39497625276668213), tuples.valueAt(50));
		assertEquals(interval(-0.3949762527666824, -0.3910309435028875), tuples.valueAt(51));
		assertEquals(interval(-0.3910309435028878, -0.38720154633118314), tuples.valueAt(52));
		assertEquals(interval(-0.3872015463311834, -0.3834824944236852), tuples.valueAt(53));
		assertEquals(interval(-0.3834824944236855, -0.37986858819879316), tuples.valueAt(54));
		assertEquals(interval(-0.37986858819879343, -0.3763549647474907), tuples.valueAt(55));
		assertEquals(interval(-0.37635496474749097, -0.3729370703141007), tuples.valueAt(56));
		assertEquals(interval(-0.37293707031410106, -0.3696106354772864), tuples.valueAt(57));
		assertEquals(interval(-0.3696106354772867, -0.3663716527236558), tuples.valueAt(58));
		assertEquals(interval(-0.3663716527236561, -0.36321635614607395), tuples.valueAt(59));
		assertEquals(interval(-0.3632163561460743, -0.36014120303280983), tuples.valueAt(60));
		assertEquals(interval(-0.36014120303281016, -0.3571428571428571), tuples.valueAt(61));
		assertEquals(interval(-0.35714285714285743, -0.35421817348790724), tuples.valueAt(62));
		assertEquals(interval(-0.3542181734879075, -0.35136418446315326), tuples.valueAt(63));
		assertEquals(interval(-0.35136418446315354, -0.34857808718787486), tuples.valueAt(64));
		assertEquals(interval(-0.34857808718787514, -0.3458572319330373), tuples.valueAt(65));
		assertEquals(interval(-0.3458572319330375, -0.34319911152729005), tuples.valueAt(66));
		assertEquals(interval(-0.3431991115272903, -0.3406013516450857), tuples.valueAt(67));
		assertEquals(interval(-0.340601351645086, -0.33806170189140655), tuples.valueAt(68));
		assertEquals(interval(-0.3380617018914068, -0.33557802760701205), tuples.valueAt(69));
		assertEquals(interval(-0.33557802760701233, -0.33314830232638465), tuples.valueAt(70));
		assertEquals(interval(-0.3331483023263849, -0.3307706008278111), tuples.valueAt(71));
		assertEquals(interval(-0.3307706008278114, -0.3284430927214309), tuples.valueAt(72));
		assertEquals(interval(-0.3284430927214312, -0.32616403652672094), tuples.valueAt(73));
		assertEquals(interval(-0.3261640365267212, -0.32393177419586827), tuples.valueAt(74));
		assertEquals(interval(-0.32393177419586855, -0.32174472604389326), tuples.valueAt(75));
		assertEquals(interval(-0.32174472604389354, -0.3196013860502963), tuples.valueAt(76));
		assertEquals(interval(-0.3196013860502966, -0.317500317500476), tuples.valueAt(77));
		assertEquals(interval(-0.3175003175004763, -0.31544014893825567), tuples.valueAt(78));
		assertEquals(interval(-0.3154401489382559, -0.3134195704036111), tuples.valueAt(79));
		assertEquals(interval(-0.31341957040361135, -0.31143732993214956), tuples.valueAt(80));
		assertEquals(interval(-0.3114373299321498, -0.30949223029508616), tuples.valueAt(81));
		assertEquals(interval(-0.30949223029508643, -0.30758312596043225), tuples.valueAt(82));
		assertEquals(interval(-0.3075831259604325, -0.3057089202578712), tuples.valueAt(83));
		assertEquals(interval(-0.3057089202578715, -0.3038685627313817), tuples.valueAt(84));
		assertEquals(interval(-0.30386856273138196, -0.3020610466650882), tuples.valueAt(85));
		assertEquals(interval(-0.30206104666508843, -0.3002854067691018), tuples.valueAt(86));
		assertEquals(interval(-0.3002854067691021, -0.2985407170132658), tuples.valueAt(87));
		assertEquals(interval(-0.298540717013266, -0.2968260885977621), tuples.valueAt(88));
		assertEquals(interval(-0.2968260885977624, -0.29514066805047734), tuples.valueAt(89));
		assertEquals(interval(-0.2951406680504776, -0.2934836354418743), tuples.valueAt(90));
		assertEquals(interval(-0.29348363544187456, -0.2918542027088892), tuples.valueAt(91));
		assertEquals(interval(-0.29185420270888945, -0.29025161208007066), tuples.valueAt(92));
		assertEquals(interval(-0.29025161208007094, -0.28867513459481253), tuples.valueAt(93));
	}

	@Test
	public void sqrtSticks() {
		IntervalTupleList tuples = functionValuesWithSampleCount("sqrt(1/sin(1/x))",
				-10, 10, -12, 12, 100);
		assertEquals(IntervalConstants.empty(), tuples.valueAt(0));
	}

	}
